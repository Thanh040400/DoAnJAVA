/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thanh
 */
public class formLoaiLinhKien extends javax.swing.JInternalFrame {
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    DefaultTableModel tableModel;
    /**
     * Creates new form formLoaiLinhKien
     */
    public formLoaiLinhKien() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI UI=(BasicInternalFrameUI)this.getUI();
        UI.setNorthPane(null);
        tableModel = (DefaultTableModel) TableLoaiLK.getModel();
        txtTenLLK.grabFocus();
        hienThiDuLieu();
        TableLoaiLK.getColumnModel().getColumn(0).setWidth(0);
        TableLoaiLK.getColumnModel().getColumn(0).setMinWidth(0);
        TableLoaiLK.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    private Connection ConnectToServer() {
        String user = "sa";
        String pass = "123456";
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLLinhKienPC_Laptop_java", user, pass);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void hienThiDuLieu() {
        try {
            conn = ConnectToServer();
            String sql = "select * from LoaiLinhKien where DaXoa=0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nhan vien
            tableModel.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaLoaiLinhKien"),
                    rs.getString("TenLoaiLinhKien"),};                   
                tableModel.addRow(row);
            }

            // cập nhật lại dữ liệu bên trong bảng nhan vien
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void refresh() {
        txtMaLLK.setText("");
        txtTenLLK.setText("");     
        txtTimKiem.setText("");
        TableLoaiLK.grabFocus();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaLLK = new javax.swing.JTextField();
        txtTenLLK = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableLoaiLK = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1396, 928));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setText("LOẠI LINH KIỆN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setText("Mã loại linh kiện:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Tên loại linh kiện:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenLLK))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaLLK)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaLLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenLLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách"));

        TableLoaiLK.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        TableLoaiLK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại linh kiện", "Tên loại linh kiện"
            }
        ));
        TableLoaiLK.setRowHeight(40);
        TableLoaiLK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableLoaiLKMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableLoaiLK);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCapNhat)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(562, 562, 562)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableLoaiLKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLoaiLKMouseClicked
        try {
            int row = TableLoaiLK.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vào dòng cần hiển thị");
                return;
            } else {

                txtMaLLK.setText(TableLoaiLK.getValueAt(row, 0).toString());
                txtTenLLK.setText(TableLoaiLK.getValueAt(row, 1).toString());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_TableLoaiLKMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            //xóa trắng dữ liệu bên trong bảng nhân viên
            tableModel.setRowCount(0);
            conn = ConnectToServer();

            String sql = "select * from LoaiLinhKien where DaXoa=0 and  ";

            if (txtTimKiem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên loại linh kiện ?");
            } else if (txtTimKiem.getText().length() > 0) {
                sql = sql + "  TenLoaiLinhKien like '%" + txtTimKiem.getText() + "%'";

                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Tên loại linh kiện " + txtTimKiem.getText() + " không tồn tại trong danh sách!");
                    txtTimKiem.setText("");
                    txtTimKiem.grabFocus();
                    return;
                }
                while (rs.next()) {
                    String[] row = new String[]{
                        rs.getString("MaLoaiLinhKien"),
                        rs.getString("TenLoaiLinhKien"),};

                    tableModel.addRow(row);
                }

                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Tìm thấy loại linh kiện có tên gần đúng là: " + txtTimKiem.getText());
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = TableLoaiLK.getSelectedRow();
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa loại linh kiện này không ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            int maLK = Integer.parseInt(TableLoaiLK.getValueAt(row, 0).toString());
            conn = ConnectToServer();
            String sql = "update LoaiLinhKien set DaXoa = 1 where MaLoaiLinhKien = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maLK);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Xóa loại linh kiện thành công");
            hienThiDuLieu();
            refresh();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        refresh();
        hienThiDuLieu();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        int row = TableLoaiLK.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn loại linh kiện trong bảng!!!");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật loại linh kiện này không ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            conn = ConnectToServer();
            String sql = "update LoaiLinhKien set TenLoaiLinhKien = ? where MaLoaiLinhKien = ? and daxoa=0 ";
            ps = conn.prepareStatement(sql);

            ps.setString(1, txtTenLLK.getText());
            ps.setInt(2, Integer.parseInt(tableModel.getValueAt(row, 0).toString()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật Loại linh kiện thành công");
            hienThiDuLieu();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
         try {
            if (txtTenLLK.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "bạn thiếu thông tin , bạn cần nhập đủ ");
            } else {
                conn = ConnectToServer();
                String sql = " insert into LoaiLinhKien (TenLoaiLinhKien, DaXoa ) values(?,? ) ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, txtTenLLK.getText());
                ps.setInt(2, 0);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "lưu Loại linh kiện thành công");
                hienThiDuLieu();
                rs.close();
                ps.close();
                conn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableLoaiLK;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtMaLLK;
    private javax.swing.JTextField txtTenLLK;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
