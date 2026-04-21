import java.sql.*;

public class RestaurantCRUD {

    // ── Change these to match your MySQL setup ──────────────────────────
    static final String URL  = "jdbc:mysql://localhost:3306/restaurantdb";
    static final String USER = "root";
    static final String PASS = "";
    // ────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.println("✅ Connected to database.\n");

            insertRecords(con);
            selectMenuItemsUnder100(con);
            selectMenuItemsInCafeJava(con);
            updatePriceUnder100(con);
            deleteItemsStartingWithP(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 1. INSERT 10 records in each table
    // ─────────────────────────────────────────────────────────────
    static void insertRecords(Connection con) throws SQLException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  INSERT RECORDS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Clear existing data (for re-runs)
        con.createStatement().executeUpdate("DELETE FROM MenuItem");
        con.createStatement().executeUpdate("DELETE FROM Restaurant");
        con.createStatement().executeUpdate("ALTER TABLE Restaurant AUTO_INCREMENT = 1");
        con.createStatement().executeUpdate("ALTER TABLE MenuItem AUTO_INCREMENT = 1");

        String insertRestaurant = "INSERT INTO Restaurant (Name, Address) VALUES (?, ?)";
        String[][] restaurants  = {
            {"Cafe Java",       "12 MG Road, Pune"},
            {"Spice Garden",    "45 FC Road, Pune"},
            {"The Biryani Hub", "78 Koregaon Park, Pune"},
            {"Pizza Palace",    "23 Camp Area, Pune"},
            {"Green Bowl",      "56 Baner, Pune"},
            {"Chai Stop",       "90 Kothrud, Pune"},
            {"The Grill House", "11 Wakad, Pune"},
            {"Dosa Corner",     "34 Hadapsar, Pune"},
            {"Sushi World",     "67 Viman Nagar, Pune"},
            {"Burger Barn",     "89 Hinjewadi, Pune"}
        };

        try (PreparedStatement ps = con.prepareStatement(insertRestaurant)) {
            for (String[] r : restaurants) {
                ps.setString(1, r[0]);
                ps.setString(2, r[1]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        System.out.println("✔ 10 Restaurant records inserted.");

        String insertMenuItem = "INSERT INTO MenuItem (Name, Price, ResId) VALUES (?, ?, ?)";
        Object[][] menuItems  = {
            {"Pasta",          80.0,  1},
            {"Pancakes",       90.0,  1},
            {"Biryani",       150.0,  3},
            {"Paneer Tikka",   95.0,  2},
            {"Pizza Margherita",200.0, 4},
            {"Green Salad",    60.0,  5},
            {"Masala Chai",    30.0,  6},
            {"Grilled Chicken",180.0, 7},
            {"Masala Dosa",    75.0,  8},
            {"Sushi Platter",  250.0, 9}
        };

        try (PreparedStatement ps = con.prepareStatement(insertMenuItem)) {
            for (Object[] m : menuItems) {
                ps.setString(1, (String) m[0]);
                ps.setDouble(2, (Double) m[1]);
                ps.setInt   (3, (Integer) m[2]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        System.out.println("✔ 10 MenuItem records inserted.\n");
    }

    // ─────────────────────────────────────────────────────────────
    // 2. SELECT MenuItem where Price <= 100
    // ─────────────────────────────────────────────────────────────
    static void selectMenuItemsUnder100(Connection con) throws SQLException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  SELECT: MenuItems with Price <= 100");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String sql = "SELECT * FROM MenuItem WHERE Price <= 100";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            printMenuItemTable(rs);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 3. SELECT MenuItem available in "Cafe Java"
    // ─────────────────────────────────────────────────────────────
    static void selectMenuItemsInCafeJava(Connection con) throws SQLException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  SELECT: MenuItems in Restaurant 'Cafe Java'");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String sql = """
                SELECT m.Id, m.Name, m.Price, m.ResId
                FROM   MenuItem m
                JOIN   Restaurant r ON m.ResId = r.Id
                WHERE  r.Name = 'Cafe Java'
                """;
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            printMenuItemTable(rs);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 4. UPDATE Price <= 100 → Price = 200
    // ─────────────────────────────────────────────────────────────
    static void updatePriceUnder100(Connection con) throws SQLException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  UPDATE: Set Price = 200 where Price <= 100");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String update = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";
        try (Statement st = con.createStatement()) {
            int rows = st.executeUpdate(update);
            System.out.println("✔ Rows updated: " + rows);
        }

        System.out.println("\n  MenuItem table AFTER update:");
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM MenuItem")) {
            printMenuItemTable(rs);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 5. DELETE MenuItem where Name starts with 'P'
    // ─────────────────────────────────────────────────────────────
    static void deleteItemsStartingWithP(Connection con) throws SQLException {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  DELETE: MenuItems whose Name starts with 'P'");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String delete = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";
        try (Statement st = con.createStatement()) {
            int rows = st.executeUpdate(delete);
            System.out.println("✔ Rows deleted: " + rows);
        }

        System.out.println("\n  MenuItem table AFTER delete:");
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM MenuItem")) {
            printMenuItemTable(rs);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Helper: Print ResultSet in tabular format
    // ─────────────────────────────────────────────────────────────
    static void printMenuItemTable(ResultSet rs) throws SQLException {
        String fmt = "| %-4s | %-20s | %-8s | %-6s |%n";
        String sep = "+------+----------------------+----------+--------+";

        System.out.println(sep);
        System.out.printf(fmt, "Id", "Name", "Price", "ResId");
        System.out.println(sep);

        int count = 0;
        while (rs.next()) {
            System.out.printf(fmt,
                rs.getInt("Id"),
                rs.getString("Name"),
                rs.getDouble("Price"),
                rs.getInt("ResId"));
            count++;
        }

        if (count == 0) System.out.printf(fmt, "-", "(no records)", "-", "-");
        System.out.println(sep);
        System.out.println("  Total rows: " + count + "\n");
    }
}