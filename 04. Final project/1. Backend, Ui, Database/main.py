import sys
import os
import sqlite3
from PyQt5 import uic
from PyQt5.QtWidgets import QApplication, QMainWindow, QAbstractSpinBox
from PyQt5.QtCore import QTimer, Qt

# ── Prices ────────────────────────────────────────────────────────────────────
PRICES = {
    "Coffee": 250,
    "Tea": 150,
    "Burger": 500,
    "Sandwhich": 250,
    "Fries": 300,
    "Pizza": 1200,
    "Brownie": 200,
    "Cookie": 150,
    "Muffin": 250,
}

# Preparation time in minutes per item
PREP_TIME = {
    "Coffee": 3,
    "Tea": 1,
    "Burger": 8,
    "Sandwhich": 5,
    "Fries": 6,
    "Pizza": 15,
    "Brownie": 0,
    "Cookie": 0,
    "Muffin": 0,
}

TAX_RATE = 0.05  # 5 %
SERVICE_RATE = 0.10  # 10 %

# ── Database Setup (Updated File Name) ─────────────────────────────────────────
CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
# Changed to your requested file name 'cafe management .db'
DB_PATH = os.path.join(CURRENT_DIR, "cafe management .db")


def init_database():
    """Creates the local database file and history table if missing."""
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()

    cursor.execute('''
        CREATE TABLE IF NOT EXISTS order_history (
            order_id INTEGER PRIMARY KEY AUTOINCREMENT,
            timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
            items_summary TEXT NOT NULL,
            subtotal REAL NOT NULL,
            tax REAL NOT NULL,
            service_charge REAL NOT NULL,
            grand_total REAL NOT NULL
        )
    ''')
    conn.commit()
    conn.close()


class BrewHaven(QMainWindow):
    def __init__(self):
        super().__init__()

        # ── Load the UI file ──────────────────────────────────────────────────
        ui_path = os.path.join(CURRENT_DIR, "cafe management .ui")
        uic.loadUi(ui_path, self)
        self.setWindowTitle("Brew Haven – Café Management")

        # ── Quantity box design only ──
        spin_boxes = [
            "coffeeSpin", "coffeeSpin_2", "coffeeSpin_3",
            "coffeeSpin_4", "coffeeSpin_5", "coffeeSpin_6",
            "coffeeSpin_7", "coffeeSpin_8", "coffeeSpin_9"
        ]

        for spin_name in spin_boxes:
            spin = getattr(self, spin_name)
            spin.setButtonSymbols(QAbstractSpinBox.UpDownArrows)
            spin.setValue(0)
            spin.lineEdit().setAlignment(Qt.AlignCenter)

        # ── Countdown timer ───────────────────────────────────────────────────
        self._remaining_seconds = 0
        self._timer = QTimer(self)
        self._timer.setInterval(1000)
        self._timer.timeout.connect(self._tick)

        # ── Wire up buttons ───────────────────────────────────────────────────
        # Welcome → Menu
        self.pushButton.clicked.connect(lambda: self.stackedWidget.setCurrentIndex(1))

        # Menu → Order review
        self.reviewOrderBtn.clicked.connect(self._go_to_order_page)

        # Order page → back to menu / forward to bill
        self.backBtn.clicked.connect(lambda: self.stackedWidget.setCurrentIndex(1))
        self.billBtn.clicked.connect(self._go_to_bill_page)

        # Bill page → start preparing (countdown) and write data to SQLite
        self.prepareBtn.clicked.connect(self._start_countdown)

        # ── SpinBox map: widget_name → (item_name, page) ─────────────────────
        self._menu_spins = {
            "coffeeSpin": "Coffee",
            "coffeeSpin_2": "Tea",
            "coffeeSpin_3": "Burger",
            "coffeeSpin_4": "Sandwhich",
            "coffeeSpin_5": "Fries",
            "coffeeSpin_6": "Pizza",
        }
        self._addon_spins = {
            "coffeeSpin_7": "Brownie",
            "coffeeSpin_8": "Cookie",
            "coffeeSpin_9": "Muffin",
        }

        # ── Live subtotal on order page when "anything else" changes ──────────
        for spin_name in self._addon_spins:
            spin = getattr(self, spin_name)
            spin.valueChanged.connect(self._update_order_page_subtotal)

        self.show()

    # ── Helpers ───────────────────────────────────────────────────────────────

    def _get_quantities(self):
        """Return {item_name: qty} for ALL items (menu + add-ons)."""
        quantities = {}
        for spin_name, item in {**self._menu_spins, **self._addon_spins}.items():
            qty = getattr(self, spin_name).value()
            if qty > 0:
                quantities[item] = qty
        return quantities

    def _calculate_totals(self, quantities):
        subtotal = sum(PRICES[item] * qty for item, qty in quantities.items())
        tax = subtotal * TAX_RATE
        service = subtotal * SERVICE_RATE
        grand = subtotal + tax + service
        prep = sum(PREP_TIME.get(item, 0) * qty for item, qty in quantities.items())
        return subtotal, tax, service, grand, prep

    # ── HTML table builder ────────────────────────────────────────────────────

    def _build_items_table(self, quantities, header=True):
        td = "padding: 4px 16px; color: #F5D79E; font-size: 15px; font-weight: bold; text-align: center;"
        th = "padding: 4px 16px; color: #E8B86D; font-size: 14px; font-weight: bold; text-align: center; border-bottom: 1px solid #E8B86D;"

        rows = ""
        if header:
            rows += f'<tr><th style="{th}">Item</th><th style="{th}">Qty</th><th style="{th}">Amount</th></tr>'

        for item, qty in quantities.items():
            amount = PRICES[item] * qty
            rows += f'<tr><td align="center" style="{td}">{item}</td><td align="center" style="{td}">x{qty}</td><td align="center" style="{td}">Rs. {amount}</td></tr>'

        return f"<html><body style='background-color:#2A1208;'><table width='100%' style='border-collapse:collapse; text-align:center;'>{rows}</table></body></html>"

    # ── Page transitions ──────────────────────────────────────────────────────

    def _go_to_order_page(self):
        quantities = {item: getattr(self, spin_name).value() for spin_name, item in self._menu_spins.items() if
                      getattr(self, spin_name).value() > 0}
        if not quantities:
            return

        self.orderSummaryText.setHtml(self._build_items_table(quantities))
        subtotal = sum(PRICES[item] * qty for item, qty in quantities.items())
        self.subtotalLabel.setText(f"Subtotal: Rs. {subtotal}")

        for spin_name in self._addon_spins:
            getattr(self, spin_name).setValue(0)

        self.stackedWidget.setCurrentIndex(2)

    def _update_order_page_subtotal(self):
        quantities = self._get_quantities()
        subtotal = sum(PRICES[item] * qty for item, qty in quantities.items())
        self.subtotalLabel.setText(f"Subtotal: Rs. {subtotal}")
        self.orderSummaryText.setHtml(self._build_items_table(quantities))

    def _go_to_bill_page(self):
        quantities = self._get_quantities()
        if not quantities:
            return

        subtotal, tax, service, grand, prep = self._calculate_totals(quantities)

        # Receipt Layout
        td = "padding: 4px 16px 4px 6px; color: #F5D79E; font-size: 15px; font-weight: bold; text-align:center;"
        th = "padding: 4px 16px 4px 6px; color: #E8B86D; font-size: 14px; border-bottom: 1px solid #E8B86D; text-align:center;"
        sep = f"<tr><td colspan='3' style='color:#E8B86D; font-size:13px; letter-spacing:2px; padding:4px 6px;'>{'─' * 36}</td></tr>"

        rows = (
            f"<tr><td colspan='3' style='color:#E8B86D; font-size:16px; font-weight:bold; text-align:center; padding:6px;'>☕  BREW HAVEN</td></tr>"
            f"<tr><td colspan='3' style='color:#F5D79E; font-size:12px; text-align:center; padding:2px 6px 6px;'>Fresh Coffee • Fast Service</td></tr>"
            f"{sep}"
            f"<tr><th style='{th}'>Item</th><th style='{th}'>Qty</th><th style='{th}'>Amount</th></tr>"
        )
        for item, qty in quantities.items():
            amount = PRICES[item] * qty
            rows += f"<tr><td align='center' style='{td}'>{item}</td><td align='center' style='{td}'>x{qty}</td><td align='center' style='{td}'>Rs. {amount}</td></tr>"
        rows += sep

        self.receiptText.setHtml(
            f"<html><body style='background-color:#2A1208;'><table style='width:100%; border-collapse:collapse; text-align:center;'>{rows}</table></body></html>")

        self.subtotalLabel_2.setText(f"Subtotal: Rs. {subtotal:.0f}")
        self.taxLabel.setText(f"Tax (5%): Rs. {tax:.0f}")
        self.serviceLabel.setText(f"Service Charge (10%): Rs. {service:.0f}")
        self.grandTotalLabel.setText(f"Grand Total: Rs. {grand:.0f}")
        self.prepTimeLabel.setText(f"Estimated Preparation Time: {prep} min")

        self.stackedWidget.setCurrentIndex(3)

    # ── Database Transaction Execution ────────────────────────────────────────
    def _save_order_to_db(self, quantities, subtotal, tax, service, grand):
        """Inserts the current invoice statistics into 'cafe management .db'."""
        try:
            conn = sqlite3.connect(DB_PATH)
            cursor = conn.cursor()

            # Merges dictionary values into text tracking, example: "Fries (x2), Tea (x1)"
            items_string = ", ".join([f"{item} (x{qty})" for item, qty in quantities.items()])

            cursor.execute('''
                INSERT INTO order_history (items_summary, subtotal, tax, service_charge, grand_total)
                VALUES (?, ?, ?, ?, ?)
            ''', (items_string, subtotal, tax, service, grand))

            conn.commit()
            conn.close()
            print("Successfully logged new purchase log to database.")
        except Exception as e:
            print(f"Database insertion failed: {e}")

    # ── Countdown ─────────────────────────────────────────────────────────────

    def _start_countdown(self):
        quantities = self._get_quantities()
        subtotal, tax, service, grand, prep_minutes = self._calculate_totals(quantities)

        # Trigger Database Storage Engine
        self._save_order_to_db(quantities, subtotal, tax, service, grand)

        self._remaining_seconds = max(prep_minutes * 60, 10)  # at least 10 s demo

        self.status1.setStyleSheet("color: #F5D79E; font: 75 12pt 'MS Shell Dlg 2';")
        self.status2.setStyleSheet("color: #888; font: 75 12pt 'MS Shell Dlg 2';")
        self.status3.setStyleSheet("color: #888; font: 75 12pt 'MS Shell Dlg 2';")
        self.statusLabel.setText("Preparing Your Order...")

        self.countdownLCD.setDigitCount(5)
        self._display_time()
        self.stackedWidget.setCurrentIndex(4)
        self._timer.start()

    def _display_time(self):
        mins = self._remaining_seconds // 60
        secs = self._remaining_seconds % 60
        self.countdownLCD.display(f"{mins}:{secs:02d}")

    def _tick(self):
        self._remaining_seconds -= 1
        self._display_time()

        total = self._get_prep_total()
        elapsed_ratio = 1 - (self._remaining_seconds / max(total, 1))

        if elapsed_ratio >= 0.33:
            self.status2.setStyleSheet("color: #F5D79E; font: 75 12pt 'MS Shell Dlg 2';")
        if elapsed_ratio >= 0.66:
            self.status3.setStyleSheet("color: #F5D79E; font: 75 12pt 'MS Shell Dlg 2';")

        if self._remaining_seconds <= 0:
            self._timer.stop()
            self.countdownLCD.display("0:00")
            self.statusLabel.setText("✅  Order Ready!")
            self.status3.setText("✅  Ready For Pickup")

    def _get_prep_total(self):
        quantities = self._get_quantities()
        return sum(PREP_TIME.get(item, 0) * qty for item, qty in quantities.items()) * 60 or 10


# ── Entry point ───────────────────────────────────────────────────────────────
if __name__ == "__main__":
    init_database()

    app = QApplication(sys.argv)
    window = BrewHaven()
    sys.exit(app.exec_())