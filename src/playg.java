import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class playg extends JFrame {
    char[] p;
    String u, Gname;
    Connection conn;
    JButton l = new JButton("Login"), r = new JButton("Register");
    JLabel exp = new JLabel("Expenditure Predictive Analysis");

    public playg() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(null);
        try {
            String url = "jdbc:mysql://localhost:3306/expend_stats";
            conn = DriverManager.getConnection(url, "root", "#$iddu2355");
            l.setBounds(10, 50, 80, 30);
            add(l);
            r.setBounds(100, 50, 80, 30);
            add(r);
            exp.setBounds(0, 0, 1000, 300);
            add(exp);

            l.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    r.setVisible(false);
                    l.setVisible(false);
                    exp.setVisible(false);
                    Linput("Login");
                }
            });

            r.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    r.setVisible(false);
                    l.setVisible(false);
                    exp.setVisible(false);
                    Rinput("Register");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    void Linput(String title) {
        setTitle(title + " Form");
        JLabel username = new JLabel("USERNAME"), password = new JLabel("PASSWORD"),
                m1 = new JLabel(),
                m2 = new JLabel();
        JButton LRButton = new JButton(title), back = new JButton("back");
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        back.setBounds(0, 0, 80, 40);
        add(back);
        username.setBounds(90, 90, 80, 40);
        add(username);
        usernameField.setBounds(10, 10, 80, 40);
        add(usernameField);
        m1.setBounds(40, 40, 80, 40);
        add(m1);
        password.setBounds(130, 130, 80, 40);
        add(password);
        passwordField.setBounds(240, 240, 80, 40);
        add(passwordField);
        m2.setBounds(350, 350, 80, 40);
        add(m2);
        LRButton.setBounds(60, 60, 80, 40);
        add(LRButton);
        m1.setVisible(false);
        m2.setVisible(false);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                r.setVisible(true);
                l.setVisible(true);
                exp.setVisible(true);

                username.setVisible(false);
                usernameField.setVisible(false);
                m1.setVisible(false);
                m2.setVisible(false);
                LRButton.setVisible(false);
                password.setVisible(false);
                passwordField.setVisible(false);
                back.setVisible(false);
                setTitle("Expenditure Predictive Analysis");
            }
        });
        LRButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m1.setVisible(false);
                m2.setVisible(false);
                u = usernameField.getText();
                p = passwordField.getPassword();
                try {
                    if (u.length() != 0 || new String(p).length() != 0) {
                        String query = String.format("SELECT * FROM users WHERE username = \'%s\'", u);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        String un, pw;
                        if (rs.next() != false) {
                            un = rs.getString("username");
                            pw = rs.getString("pass");
                            if (new String(p).equals(pw)) {
                                username.setVisible(false);
                                usernameField.setVisible(false);
                                m1.setVisible(false);
                                m2.setVisible(false);
                                LRButton.setVisible(false);
                                password.setVisible(false);
                                passwordField.setVisible(false);
                                back.setVisible(false);
                                System.out.println("Logged In..");
                                expensesInput();
                            } else {
                                m2.setText("Incorrect Password");
                                m2.setVisible(true);
                            }
                        } else {
                            m1.setText("Incorrect Username");
                            m1.setVisible(true);
                        }
                    } else {

                        if (u.length() == 0) {
                            m1.setText("Username is Required");
                            m1.setVisible(true);
                        }
                        if (new String(p).length() == 0) {
                            m2.setText("Password is Required");
                            m2.setVisible(true);
                        }
                    }
                } catch (SQLException ec) {
                    System.out.println(ec.getMessage());
                }
            }
        });
    }

    void Rinput(String title) {
        setTitle(title + " Form");
        JLabel username = new JLabel("USERNAME"), password = new JLabel("PASSWORD"), name = new JLabel("NAME"),
                age = new JLabel("AGE"),
                m1 = new JLabel(),
                m2 = new JLabel();
        JButton LRButton = new JButton(title), back = new JButton("back"), credentialsButton = new JButton("SAVE");
        JTextField usernameField = new JTextField(10), nameField = new JTextField(10),
                ageField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        back.setBounds(0, 0, 80, 40);
        add(back);
        username.setBounds(30, 30, 80, 40);
        add(username);
        usernameField.setBounds(60, 60, 80, 40);
        add(usernameField);
        m1.setBounds(90, 90, 80, 40);
        add(m1);
        password.setBounds(120, 120, 80, 40);
        add(password);
        passwordField.setBounds(160, 160, 80, 40);
        add(passwordField);
        m2.setBounds(200, 200, 80, 40);
        add(m2);
        LRButton.setBounds(240, 240, 80, 40);
        add(LRButton);
        m1.setVisible(false);
        m2.setVisible(false);

        // namePanel
        name.setBounds(0, 0, 80, 40);
        add(name);
        nameField.setBounds(40, 40, 80, 40);
        add(nameField);
        age.setBounds(70, 70, 80, 40);
        add(age);
        ageField.setBounds(100, 100, 80, 40);
        add(ageField);
        credentialsButton.setBounds(150, 150, 80, 40);
        add(credentialsButton);
        name.setVisible(false);
        age.setVisible(false);
        nameField.setVisible(false);
        credentialsButton.setVisible(false);
        ageField.setVisible(false);

        credentialsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String n = nameField.getText();
                int a = Integer.parseInt(ageField.getText());
                try {
                    PreparedStatement stmt = conn
                            .prepareStatement("INSERT INTO customers(name, age, username) VALUES(?, ?, ?)");
                    stmt.setString(1, n);
                    stmt.setInt(2, a);
                    stmt.setString(3, u);
                    stmt.executeUpdate();
                    Gname = n;
                    name.setVisible(false);
                    age.setVisible(false);
                    nameField.setVisible(false);
                    credentialsButton.setVisible(false);
                    ageField.setVisible(false);
                    expensesInput();
                } catch (SQLException g) {
                    System.out.println(g.getMessage());
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back.setVisible(false);
                username.setVisible(false);
                usernameField.setVisible(false);
                LRButton.setVisible(false);
                m1.setVisible(false);
                m2.setVisible(false);
                password.setVisible(false);
                passwordField.setVisible(false);
                setTitle("Expenditure Predictive Analysis");
                r.setVisible(true);
                l.setVisible(true);
                exp.setVisible(true);
            }
        });

        LRButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m1.setVisible(false);
                u = usernameField.getText();
                p = passwordField.getPassword();
                try {
                    String query = String.format("SELECT * FROM users WHERE username = \'%s\'", u);
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    if (!rs.next()) {
                        PreparedStatement stmt = conn
                                .prepareStatement("INSERT INTO users (username, pass) VALUES (?, ?)");
                        stmt.setString(1, u);
                        stmt.setString(2, new String(p));
                        stmt.executeUpdate();
                        // panel-false
                        back.setVisible(false);
                        username.setVisible(false);
                        usernameField.setVisible(false);
                        LRButton.setVisible(false);
                        m1.setVisible(false);
                        m2.setVisible(false);
                        password.setVisible(false);
                        passwordField.setVisible(false);
                        // nPanel-true
                        name.setVisible(true);
                        age.setVisible(true);
                        nameField.setVisible(true);
                        credentialsButton.setVisible(true);
                        ageField.setVisible(true);
                    } else {
                        m1.setText("Username already exists..");
                        m1.setVisible(true);
                    }
                    if (u.length() == 0) {
                        m1.setText("Username is Required.");
                        m1.setVisible(true);
                    }
                    if (new String(p).length() == 0) {
                        m2.setText("Password is Required.");
                        m2.setVisible(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    void expensesInput() {
        JLabel expen = new JLabel("Expenditure");
        JLabel prev = new JLabel();
        JButton save = new JButton("SAVE"), addNew = new JButton("ADD New Expenditure"),
                viewPrev = new JButton("View Previous Month's Expenses"), logout = new JButton("Log Out"),
                graphView = new JButton("View Graph"), goBack = new JButton("Back");
        System.out.println(Gname);
        JTextField t = new JTextField(10);

        setTitle(Gname);
        addNew.setBounds(0, 0, 80, 40);
        add(addNew);
        viewPrev.setBounds(40, 40, 80, 40);
        add(viewPrev);
        logout.setBounds(70, 70, 80, 40);
        add(logout);
        graphView.setBounds(120, 120, 80, 40);
        add(graphView);
        goBack.setBounds(150, 150, 80, 40);
        add(goBack);
        t.setBounds(190, 190, 80, 40);
        add(t);
        expen.setBounds(230, 230, 800, 40);
        add(expen);
        save.setBounds(270, 270, 80, 40);
        add(save);

        goBack.setVisible(false);
        expen.setVisible(false);
        save.setVisible(false);
        t.setVisible(false);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // panel-false
                goBack.setVisible(false);
                expen.setVisible(false);
                save.setVisible(false);
                t.setVisible(false);
                addNew.setVisible(false);
                viewPrev.setVisible(false);
                logout.setVisible(false);
                graphView.setVisible(false);
                prev.setVisible(false);
                // pan-true
                exp.setVisible(true);
                r.setVisible(true);
                l.setVisible(true);
                setTitle("Expenditure Predictive Analysis");
            }
        });

        addNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save.setVisible(true);
                viewPrev.setVisible(false);
                addNew.setVisible(false);
                graphView.setVisible(false);
                goBack.setVisible(true);
                expen.setVisible(true);
                t.setVisible(true);
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = String.format("SELECT * FROM customers WHERE username =\'%s\'", u);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    String ex = t.getText(), res = "0";
                    if (ex.length() != 0) {
                        if (rs.next() != false) {
                            String expen = rs.getString("expenses");
                            if (expen == null) {
                                res = ex;
                            } else {
                                res = expen + "-" + ex;
                            }
                        } else {
                            System.out.println("none present");
                        }
                        String q1 = "UPDATE customers SET expenses = \'" + res + "\' WHERE username =\'" + u + "\'";
                        PreparedStatement st = conn.prepareStatement(q1);
                        st.executeUpdate();
                        expen.setVisible(false);
                        save.setVisible(false);
                        addNew.setVisible(true);
                        viewPrev.setVisible(true);
                        graphView.setVisible(true);
                        goBack.setVisible(false);
                        prev.setVisible(false);
                        t.setVisible(false);
                    } else {
                        System.out.println("enter text");
                    }
                } catch (SQLException eq) {
                    System.out.println(eq.getMessage());
                }
            }
        });

        viewPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prev.setBounds(300, 300, 80, 40);
                add(prev);
                prev.setVisible(false);
                goBack.setVisible(true);
                expen.setVisible(false);
                save.setVisible(false);
                addNew.setVisible(false);
                viewPrev.setVisible(false);
                graphView.setVisible(false);
                try {
                    String query = String.format("SELECT * FROM customers WHERE username =\'%s\'", u);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next() != false) {
                        String expen2 = rs.getString("expenses");
                        if (expen2 == null) {
                            prev.setText("No Expenses entered previously..");
                        } else {
                            prev.setText(expen2);
                        }
                        prev.setVisible(true);
                    } else {
                        System.out.println("none present");
                    }
                } catch (SQLException eq) {
                    System.out.println(eq.getMessage());
                }
            }
        });

        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expen.setVisible(false);
                save.setVisible(false);
                addNew.setVisible(true);
                viewPrev.setVisible(true);
                graphView.setVisible(true);
                goBack.setVisible(false);
                prev.setVisible(false);
                t.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new playg();
    }
}
