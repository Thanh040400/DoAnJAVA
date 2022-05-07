/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import controller.Validation;
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
public class formNhaCungCap extends javax.swing.JInternalFrame {
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    DefaultTableModel tableModel;
    /**
     * Creates new form formNhaCungCap
     */
    public formNhaCungCap() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI UI=(BasicInternalFrameUI)this.getUI();
        UI.setNorthPane(null);
        tableModel = (DefaultTableModel) tblNhaCungCap.getModel();
        txtTenNhaCungCap.grabFocus();
        hienThiDuLieu();
        tblNhaCungCap.getColumnModel().getColumn(0).setWidth(0);
        tblNhaCungCap.getColumnModel().getColumn(0).setMinWidth(0);
        tblNhaCungCap.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNhaCungCap = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1396, 928));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setText("NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(578, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(574, 574, 574))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        btnThem.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtTimKiem.setText("Tìm Kiếm Thông Tin Ở Đây !!!");
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton1.setText("Thêm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnThemLayout = new javax.swing.GroupLayout(btnThem);
        btnThem.setLayout(btnThemLayout);
        btnThemLayout.setHorizontalGroup(
            btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnThemLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnThemLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnThemLayout.setVerticalGroup(
            btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThemLayout.createSequentialGroup()
                .addGroup(btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnThemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chung "));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Tên nhà cung cấp:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        txtTenNhaCungCap.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(txtSDT)
                    .addComponent(txtDiaChi))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));

        tblNhaCungCap.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "SĐT", "Địa Chỉ"
            }
        ));
        tblNhaCungCap.setRowHeight(40);
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhaCungCap);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Connection ConnectToDataBase() {
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
            conn = ConnectToDataBase();
            String sql = "select MaNCC, TenNCC, DiaChi, SDT from NHACUNGCAP where DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //Xóa dữ liệu bên trong bảng nhà cung cấp
            tableModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaNCC"),
                    rs.getString("TenNCC"),
                    rs.getString("SDT"),
                    rs.getString("DiaChi"),};
                tableModel.addRow(row);
            }

            // cập nhật lại dữ liệu bên trong bảng nhà cung cấp
            tableModel.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void refresh() {
        //txtMaNhaCungCap.setText("");
        txtTenNhaCungCap.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtTimKiem.setText("");
        txtTenNhaCungCap.grabFocus();
    }
    
    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            //xóa trắng dữ liệu bên trong bảng nhà cung cấp
            tableModel.setRowCount(0);
            txtTenNhaCungCap.setText("");
            txtDiaChi.setText("");
            txtSDT.setText("");
            conn = ConnectToDataBase();

            String sql = "select * from NHACUNGCAP ";
            if (txtTimKiem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà cung cấp");
            } else if (txtTimKiem.getText().length() > 0) {
                sql = sql + " where DaXoa=0 and TenNCC like N'%" + txtTimKiem.getText() + "%'";

                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Tên nhà cung cấp: " + txtTimKiem.getText() + " không tồn tại trong danh sách!");
                    txtTimKiem.setText("");
                    txtTimKiem.grabFocus();
                    return;
                }
                while (rs.next()) {
                    String[] row = new String[]{
                        rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi"),};
                    tableModel.addRow(row);
                }
                tableModel.fireTableDataChanged();

                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.grabFocus();
        txtTenNhaCungCap.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblNhaCungCap.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhà cung cấp để xóa");
            return;
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhà nhà cung cấp ?") == JOptionPane.NO_OPTION) {
                return;
            }
            try {
                int maLK = Integer.parseInt(tblNhaCungCap.getValueAt(row, 0).toString());
                conn = ConnectToDataBase();
                String sql = "update NHACUNGCAP set daxoa = 1 where MaNCC = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, maLK);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Xóa nhà cung cấp thành công");
                hienThiDuLieu();
                refresh();
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        int row = tblNhaCungCap.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhà cung cấp trong bảng!!!");
            return;
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật nhà cung cấp ?") == JOptionPane.NO_OPTION) {
                return;
            }
            try {
                conn = ConnectToDataBase();
                String sql = "update  NHACUNGCAP set TenNCC = ?, SDT = ?, DiaChi = ?, DaXoa = ?"
                + "     where MaNCC = ? ";
                ps = conn.prepareStatement(sql);

                ps.setString(1, txtTenNhaCungCap.getText());
                ps.setString(2, txtSDT.getText());
                ps.setString(3, txtDiaChi.getText());
                ps.setInt(4, 0);
                ps.setString(5, tableModel.getValueAt(row, 0).toString());

                ps.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật nhà cung cấp thành công");
                hienThiDuLieu();
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            StringBuffer sb = new StringBuffer();

            Validation.check(txtTenNhaCungCap, sb, "Tên nhà cung cấp không được để trống");
            Validation.check(txtDiaChi, sb, "Địa chỉ không được để trống");
            while (true) {
                if (txtSDT.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
                    txtSDT.grabFocus();
                    return;
                } else if (txtSDT.getText().trim().length() > 11 || txtSDT.getText().trim().length() < 10) {
                    JOptionPane.showMessageDialog(this, "SĐT chứa từ 10-11 số.");
                    txtSDT.grabFocus();
                    return;
                } else if (!txtSDT.getText().trim().matches("0[1-9]{1}\\d{8,9}")) {
                    JOptionPane.showMessageDialog(this, "SĐT chưa đúng định dạng.");
                    txtSDT.grabFocus();
                    return;
                } else {
                    break;
                }
            }
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn = ConnectToDataBase();
            String sql = "insert into NHACUNGCAP(TenNCC,SDT,DiaChi,DaXoa) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, txtTenNhaCungCap.getText());
            ps.setString(2, txtSDT.getText());
            ps.setString(3, txtDiaChi.getText());
            ps.setInt(4, 0);

            ps.executeUpdate();

            hienThiDuLieu();
            JOptionPane.showMessageDialog(this, "Lưu nhà cung cấp thành công");
            refresh();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        try {
            int row = tblNhaCungCap.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vào dòng cần hiển thị");
                return;
            } else {
                //txtMaNhaCungCap.setText(tblNhaCungCap.getValueAt(row, 0).toString());
                txtTenNhaCungCap.setText(tblNhaCungCap.getValueAt(row, 1).toString());
                txtSDT.setText(tblNhaCungCap.getValueAt(row, 2).toString());
                txtDiaChi.setText(tblNhaCungCap.getValueAt(row, 3).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblNhaCungCapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JPanel btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhaCungCap;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(() -> {
            new formNhaCungCap().setVisible(true);
        });
    }
}
