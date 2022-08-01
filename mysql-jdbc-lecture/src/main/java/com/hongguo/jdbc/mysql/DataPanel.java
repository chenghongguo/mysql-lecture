package com.hongguo.jdbc.mysql;

import javax.sql.RowSet;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataPanel extends JPanel {

    private List<JTextField> fields;

    public DataPanel(RowSet rowSet) throws SQLException {
        fields = new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        ResultSetMetaData metaData = rowSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            gbc.gridy = i - 1;

            String columnName = metaData.getColumnName(i);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(columnName), gbc);

            int columnWidth = metaData.getColumnDisplaySize(i);
            JTextField tb = new JTextField(columnWidth);
            if (!metaData.getColumnClassName(i).equals("java.lang.String")) {
                tb.setEditable(false);
            }

            fields.add(tb);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(tb, gbc);
        }
    }

    public void showRow(ResultSet rs) {
        try {
            if (rs == null) {
                return;
            }
            for (int i = 1; i <= fields.size(); i++) {
                String field = rs.getString(i);
                JTextField tb = fields.get(i - 1);
                tb.setText(field);
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void setRow(ResultSet rs) throws SQLException {
        for (int i = 1; i <= fields.size(); i++) {
            String field = rs.getString(i);
            JTextField tb = fields.get(i - 1);
            if (!field.equals(tb.getText())) {
                rs.updateString(i, tb.getText());
            }
        }
        rs.updateRow();
    }
}
