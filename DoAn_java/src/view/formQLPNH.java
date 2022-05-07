/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thanh
 */
public class formQLPNH extends javax.swing.JInternalFrame {
    public Connection conn;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    DefaultTableModel tableModel_PNH;
    DefaultTableModel tableModel_CT_PNH;
    /**
     * Creates new form formQLPNH
     */
    public formQLPNH() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI UI=(BasicInternalFrameUI)this.getUI();
        UI.setNorthPane(null);
        tableModel_PNH = (DefaultTableModel) tablePNH.getModel();
        tableModel_CT_PNH = (DefaultTableModel) table_CT.getModel();
        layThongTinPNH();
        layTenNhanVien_LenCBB();
        layTenNhaCungCap_LenCBB();
        layTenLoaiLK();

        //Ẩn thông tin cột mã phiếu nhập hàng
        tablePNH.getColumnModel().getColumn(0).setWidth(0);
        tablePNH.getColumnModel().getColumn(0).setMinWidth(0);
        tablePNH.getColumnModel().getColumn(0).setMaxWidth(0);

        //Ẩn thông tin cột Mã chi tiết phiếu nhập hàng, Mã linh kiện, Loại linh kiện
        table_CT.getColumnModel().getColumn(0).setWidth(0);
        table_CT.getColumnModel().getColumn(0).setMinWidth(0);
        table_CT.getColumnModel().getColumn(0).setMaxWidth(0);

        table_CT.getColumnModel().getColumn(1).setWidth(0);
        table_CT.getColumnModel().getColumn(1).setMinWidth(0);
        table_CT.getColumnModel().getColumn(1).setMaxWidth(0);

        table_CT.getColumnModel().getColumn(2).setWidth(0);
        table_CT.getColumnModel().getColumn(2).setMinWidth(0);
        table_CT.getColumnModel().getColumn(2).setMaxWidth(0);
    }

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

    public void lamMoiPhieuNhapHang() {
        txtDiaChi_NV.setText("");
        txtSDT_NV.setText("");
        txtDiaChi_NCC.setText("");
        txtSDT_NCC.setText("");
        txtTongTien.setText("");
    }

    public void lamMoiCTPhieuNhapHang() {
        txtTen_SP.setText("");
        txtXuatSu_SP.setText("");
        txtBaoHanh.setText("");
        txtGia_SP.setText("");
        txtSL.setText("");
        txtThanhTien.setText("");
    }

    private void layThongTinPNH() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.MaPNH, b.HoTen, c.TenNCC, a.NgayNhapHang, a.TongTien\n"
                    + "from PhieuNhapHang a, NHANVIEN b, NHACUNGCAP c\n"
                    + "where a.MANV = b.MANV and a.MaNCC = c.MaNCC and a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            tableModel_PNH.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaPNH"),
                    rs.getString("HoTen"),
                    rs.getString("TenNCC"),
                    rs.getString("NgayNhapHang"),
                    rs.getString("TongTien"),};
                tableModel_PNH.addRow(row);
            }
            tableModel_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối với cơ sở dữ liệu");
            e.printStackTrace();
        }
    }

    private void layThongTin_CT_PNH_TheoMa(int maPNH) {
        try {

            conn = ConnectToDataBase();
            String sql = "select a.MaCTPNH, a.MaLinhKien, a.LoaiLinhKien, c.TenLoaiLinhKien, b.TenLinhKien, a.XuatSu, a.BaoHanh, a.GiaBan, a.SoLuongNhap, a.ThanhTien\n"
                    + "from CTPNH a, LinhKien b, LoaiLinhKien c, PhieuNhapHang d\n"
                    + "where a.MaPNH = ? and a.MaLinhKien = b.MaLinhKien and a.LoaiLinhKien = c.MaLoaiLinhKien and a.MaPNH = d.MaPNH and d.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maPNH);
            rs = ps.executeQuery();
            tableModel_CT_PNH.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaCTPNH"),
                    rs.getString("MaLinhKien"),
                    rs.getString("LoaiLinhKien"),
                    rs.getString("TenloaiLinhKien"),
                    rs.getString("TenLinhKien"),
                    rs.getString("XuatSu"),
                    rs.getString("BaoHanh"),
                    rs.getString("GiaBan"),
                    rs.getString("SoLuongNhap"),
                    rs.getString("ThanhTien"),};
                tableModel_CT_PNH.addRow(row);
            }
            tableModel_CT_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void layTenNhanVien_LenCBB() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.HoTen from NHANVIEN a where a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("HoTen"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbNhanVien.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void layTenNhaCungCap_LenCBB() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.TenNCC from NhaCungCap a where a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("TenNCC"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbNhaCungCap.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void timKiem_PNH(String ten_NCC) {
        try {
            conn = ConnectToDataBase();
            tableModel_PNH.setRowCount(0);
            String sql = "select a.MaPNH, b.HoTen, c.TenNCC, a.NgayNhapHang, a.TongTien from PhieuNhapHang a, NHANVIEN b, NHACUNGCAP c\n"
                    + "where a.DaXoa = 0 and a.MaNCC = c.MaNCC and a.MANV = b.MANV and c.TenNCC like N'%" + ten_NCC + "%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp " + txtTimKiem.getText() + " không tồn tại trong danh sách!");
                txtTimKiem.setText("");
                txtTimKiem.grabFocus();
                return;
            }
            while (rs.next()) {
                Vector<Object> vec = new Vector<>();
                vec.add(rs.getInt("MaPNH"));
                vec.add(rs.getString("Hoten"));
                vec.add(rs.getString("TenNCC"));
                vec.add(rs.getDate("NgayNhapHang"));
                vec.add(rs.getLong("TongTien"));
                tableModel_PNH.addRow(vec);
            }
            tableModel_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private int lay_Ma_NV_Theo_TenNV(String TenNV) {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select MANV from NHANVIEN where DaXoa=0 and HoTen=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TenNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MANV");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private int lay_MaNCC_Theo_Ten_NCC(String ten_NCC) {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select MaNCC from NhaCungCap where TenNCC=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ten_NCC);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MaNCC");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private void layTenLoaiLK() {
        try {
            conn = ConnectToDataBase();
            String sql = "select TenLoaiLinhkien from LoaiLinhKien";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("TenLoaiLinhkien"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbLoai_SP.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void capNhat_PNH(int maPNH, int maNV, int maNCC) {
        try {
            conn = ConnectToDataBase();
            String sql = "update PhieuNhapHang set MaNV = ? , MaNCC = ? where MaPNH =? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(3, maPNH);
            ps.setInt(1, maNV);
            ps.setInt(2, maNCC);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void capNhat_CT_PNH(int maCT_PNH, int maPNH, String tenLK, String xuatSu, String baoHanh, int sl, double giaBan, double thanhTien) {
        try {
            conn = ConnectToDataBase();
            String sql = "update CTPNH set TenLinhKien = ?, XuatSu= ?,BaoHanh = ?,SoLuongNhap=?,GiaBan = ?,ThanhTien=? where MaPNH=? and MaCTPNH=? and MaLinhKien = MaLinhKien";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenLK);
            ps.setString(2, xuatSu);
            ps.setString(3, baoHanh);
            ps.setInt(4, sl);
            ps.setDouble(5, giaBan);
            ps.setDouble(6, thanhTien);
            ps.setInt(7, maPNH);
            ps.setInt(8, maCT_PNH);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void xoa_PNH(int maPNH) {
        try {
            conn = ConnectToDataBase();
            String sql = "update PhieuNhapHang set DaXoa=1 where maPNH=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maPNH);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void xoa_CT_PNH(int ma_CT_PNH, int ma_PNH, int ma_LK) {
        try {
            conn = ConnectToDataBase();
            String sql = "delete from CTPNH  where MaCTPNH = ? and maPNH = ? and MaLinhKien = ? AND MaLinhKien = LoaiLinhKien";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ma_CT_PNH);
            ps.setInt(2, ma_PNH);
            ps.setInt(3, ma_LK);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private long layTongTien_SauKhiXoa_CTPNH(int ma_PNH) {
        long tongtien = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select thanhtien from CTPNH a, PhieuNhapHang b where a.MaPNH = b.MaPNH and b.MaPNH = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ma_PNH);
            rs = ps.executeQuery();
            while (rs.next()) {
                long thanhTien = rs.getLong("ThanhTien");
                tongtien = tongtien + thanhTien;
            }
            return tongtien;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
        return tongtien;
    }

    private void capNhat_TongTien_SauKhi_Xoa_CTPNH(int ma_PNH, long tongTien) {
        try {
            conn = ConnectToDataBase();
            String sql = "update PhieuNhapHang set Tongtien=? where MaPNH=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, tongTien);
            ps.setInt(2, ma_PNH);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_CT = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePNH = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSDT_NV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi_NV = new javax.swing.JTextField();
        cbbNhaCungCap = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT_NCC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi_NCC = new javax.swing.JTextField();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTen_SP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtXuatSu_SP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGia_SP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtBaoHanh = new javax.swing.JTextField();
        cbbLoai_SP = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng chi tiết phiếu nhập hàng"));

        table_CT.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        table_CT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chi Tiết Phiếu Nhập Hàng", "Mã Linh Kiên", "Mã Loại Linh Kiên", "Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Xuất Sứ", "Bảo Hành", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        table_CT.setRowHeight(40);
        table_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_CTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_CT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tablePNH.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tablePNH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập Hàng", "Tên Nhân Viên", "Tên Nhà Cung Cấp", "Ngày Lập Phiếu", "Tổng Tiền"
            }
        ));
        tablePNH.setRowHeight(40);
        tablePNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePNHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePNH);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin phiếu nhập hàng"));
        jPanel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setText("Tên Nhân Viên:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        txtSDT_NV.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel4.setText("Địa Chỉ:");

        txtDiaChi_NV.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        cbbNhaCungCap.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        cbbNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhaCungCapActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel5.setText("Tên Nhà Cung Cấp:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel6.setText("Số Điện Thoại:");

        txtSDT_NCC.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        txtDiaChi_NCC.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        cbbNhanVien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        cbbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSDT_NV)
                    .addComponent(cbbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi_NV))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbbNhaCungCap, 0, 263, Short.MAX_VALUE)
                    .addComponent(txtSDT_NCC)
                    .addComponent(txtDiaChi_NCC))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel4)
                        .addComponent(txtDiaChi_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnCapNhat.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtTimKiem.setText("Tìm kiếm ở đây !!!");
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phiếu nhập hàng"));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel9.setText("Loại Sản Phẩm");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel10.setText("Tên Sản Phẩm");

        txtTen_SP.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel11.setText("Xuất Sứ");

        txtXuatSu_SP.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel12.setText("Giá Bán");

        txtGia_SP.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtGia_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGia_SPMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel13.setText("Số Lượng");

        txtSL.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtSL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel14.setText("Thành Tiền");

        txtThanhTien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel15.setText("Bảo Hành");

        txtBaoHanh.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        cbbLoai_SP.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSL, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGia_SP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtXuatSu_SP)
                            .addComponent(txtThanhTien)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen_SP)
                            .addComponent(cbbLoai_SP, 0, 167, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbLoai_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtXuatSu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel8.setText("TỔNG TIỀN :");

        txtTongTien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setText("QUẢN LÝ PHIẾU NHẬP HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(494, 494, 494)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE))
                            .addComponent(txtTongTien)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("THÔNG TIN PHIẾU NHẬP HÀNG");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_CTMouseClicked
        try {
            int row = table_CT.getSelectedRow();
            if (row >= 0) {
                cbbLoai_SP.getModel().setSelectedItem(tableModel_CT_PNH.getValueAt(row, 3));
                txtTen_SP.setText(table_CT.getValueAt(row, 4).toString());
                txtXuatSu_SP.setText(table_CT.getValueAt(row, 5).toString());
                txtBaoHanh.setText(table_CT.getValueAt(row, 6).toString());
                txtGia_SP.setText(table_CT.getValueAt(row, 7).toString());
                txtSL.setText(table_CT.getValueAt(row, 8).toString());
                long thanhTien = Long.parseLong(tableModel_CT_PNH.getValueAt(row, 9).toString());
                Locale VN = new Locale("vi", "VN");
                Currency vnd = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtThanhTien.setText(VNDFormat.format(thanhTien));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_table_CTMouseClicked

    private void tablePNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePNHMouseClicked
        try {
            int row_PNH = tablePNH.getSelectedRow();
            int maPNH = Integer.parseInt(tableModel_PNH.getValueAt(row_PNH, 0).toString());
            if (row_PNH >= 0) {
                cbbNhanVien.setSelectedItem(tableModel_PNH.getValueAt(row_PNH, 1).toString());
                cbbNhaCungCap.setSelectedItem(tableModel_PNH.getValueAt(row_PNH, 2).toString());
                // txtTongTien.setText(tablePNH.getValueAt(row, 4).toString());
                long tongTien = Long.parseLong(tableModel_PNH.getValueAt(row_PNH, 4).toString());
                Locale VN = new Locale("vi", "VN");
                Currency vnd = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtTongTien.setText(VNDFormat.format(tongTien));
                layThongTin_CT_PNH_TheoMa(maPNH);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tablePNHMouseClicked

    private void cbbNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhaCungCapActionPerformed
        try {
            String tenNhaCungCap = (String) cbbNhaCungCap.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "Select *from NhaCungCap where DaXoa = 0 and TenNCC = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenNhaCungCap);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                txtSDT_NCC.setText(rs.getString("SDT"));
                txtDiaChi_NCC.setText(rs.getString("DiaChi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbNhaCungCapActionPerformed

    private void cbbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhanVienActionPerformed
        try {
            String hoTen = (String) cbbNhanVien.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "Select *from NhanVien where DaXoa = 0 and HoTen = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, hoTen);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                txtSDT_NV.setText(rs.getString("SDT"));
                txtDiaChi_NV.setText(rs.getString("DiaChi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbNhanVienActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            try {
                int row_PNH = tablePNH.getSelectedRow();
                int maPNH = Integer.parseInt(tableModel_PNH.getValueAt(row_PNH, 0).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn phiếu nhập hàng!!!");
                return;
            }
            int row_PNH = tablePNH.getSelectedRow();
            int row_CT_PNH = table_CT.getSelectedRow();
            int maPNH = Integer.parseInt(tableModel_PNH.getValueAt(row_PNH, 0).toString());
            int maNV = lay_Ma_NV_Theo_TenNV(cbbNhanVien.getSelectedItem().toString());
            int maNCC = lay_MaNCC_Theo_Ten_NCC(cbbNhaCungCap.getSelectedItem().toString());
            if (row_PNH >= 0 && row_CT_PNH >= 0) {
                int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật, phiếu nhập hàng này không?");
                if (rs == JOptionPane.YES_OPTION) {
                    try {
                        int soLuong = Integer.parseInt(txtSL.getText());
                        if (soLuong < 0) {
                            JOptionPane.showMessageDialog(rootPane, "Số lượng phải lớn hơn 0");
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Số lượng phải là số");
                        return;
                    }

                    try {
                        long giaBan = Long.parseLong(txtGia_SP.getText());
                        if (giaBan < 0) {
                            JOptionPane.showMessageDialog(rootPane, "Giá sản phẩm phải lớn hơn 0");
                            return;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Giá sản phẩm phải là số");
                        return;
                    }

                    int ma_CT_PNH = Integer.parseInt(tableModel_CT_PNH.getValueAt(row_CT_PNH, 0).toString());
                    int soLuong = Integer.parseInt(txtSL.getText());
                    double gia = Double.parseDouble(txtGia_SP.getText());
                    int maLK = Integer.parseInt(tableModel_CT_PNH.getValueAt(row_CT_PNH, 1).toString());
                    String tenLK = txtTen_SP.getText();
                    String xuatSu = txtXuatSu_SP.getText();
                    String baoHanh = txtBaoHanh.getText();
                    double thanhTien = soLuong * gia;
                    Double giaBan = Double.parseDouble(txtGia_SP.getText());
                    capNhat_CT_PNH(ma_CT_PNH, maPNH, tenLK, xuatSu, baoHanh, soLuong, gia, thanhTien);
                    long tongtiensauKhiXoa = layTongTien_SauKhiXoa_CTPNH(maPNH);
                    capNhat_TongTien_SauKhi_Xoa_CTPNH(maPNH, tongtiensauKhiXoa);
                    layThongTinPNH();
                    layThongTin_CT_PNH_TheoMa(maPNH);
                    lamMoiPhieuNhapHang();
                    lamMoiCTPhieuNhapHang();
                    txtTongTien.setText("");
                }
            } else {
                int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật, phiếu nhập hàng này không?");
                if (rs == JOptionPane.YES_OPTION) {
                    capNhat_PNH(maPNH, maNV, maNCC);
                    JOptionPane.showMessageDialog(this, "Cập nhật thông tin phiếu nhập hàng thành công!!!");
                    layThongTinPNH();
                    lamMoiPhieuNhapHang();

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {

            try {
                int row_PNH = tablePNH.getSelectedRow();
                int ma_PNH = Integer.parseInt(tableModel_PNH.getValueAt(row_PNH, 0).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn phiếu nhập hàng để xóa!!!");
                return;
            }

            int row_PNH = tablePNH.getSelectedRow();
            int ma_PNH = Integer.parseInt(tableModel_PNH.getValueAt(row_PNH, 0).toString());
            int row_CT_PNH = table_CT.getSelectedRow();

            if (row_PNH >= 0 && row_CT_PNH >= 0) {
                int ma_CT_PNH = Integer.parseInt(tableModel_CT_PNH.getValueAt(row_CT_PNH, 0).toString());
                int ma_LK = Integer.parseInt(tableModel_CT_PNH.getValueAt(row_CT_PNH, 1).toString());
                int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa, chi tiết phiếu nhập hàng này không?");
                if (rs == JOptionPane.YES_OPTION) {
                    xoa_CT_PNH(ma_CT_PNH, ma_PNH, ma_LK);
                    long tongtien = layTongTien_SauKhiXoa_CTPNH(ma_PNH);
                    capNhat_TongTien_SauKhi_Xoa_CTPNH(ma_PNH, tongtien);
                    layThongTinPNH();
                    layThongTin_CT_PNH_TheoMa(ma_PNH);
                    lamMoiPhieuNhapHang();
                    lamMoiCTPhieuNhapHang();
                }
            } else if (row_PNH >= 0) {
                int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa, phiếu nhập hàng này không?");
                if (rs == JOptionPane.YES_OPTION) {
                    xoa_PNH(ma_PNH);
                    JOptionPane.showMessageDialog(this, "Xóa phiếu nhập hàng thành công!!!");
                    layThongTinPNH();
                    lamMoiPhieuNhapHang();
                    lamMoiCTPhieuNhapHang();
                } else if (row_CT_PNH >= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn không thể xóa chi tiết phiếu nhập hàng, khi không lựa chọn phiếu nhập hàng tương ứng ?");
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            if (txtTimKiem.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà cung cấp cần tìm");
                txtTimKiem.grabFocus();
            } else {
                timKiem_PNH(txtTimKiem.getText());
                lamMoiPhieuNhapHang();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.grabFocus();
        tableModel_CT_PNH.setRowCount(0);
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiPhieuNhapHang();
        lamMoiCTPhieuNhapHang();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtGia_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGia_SPMouseClicked
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtGia_SPMouseClicked

    private void txtSLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLMouseClicked
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtSLMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoai_SP;
    private javax.swing.JComboBox<String> cbbNhaCungCap;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablePNH;
    private javax.swing.JTable table_CT;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDiaChi_NCC;
    private javax.swing.JTextField txtDiaChi_NV;
    private javax.swing.JTextField txtGia_SP;
    private javax.swing.JTextField txtSDT_NCC;
    private javax.swing.JTextField txtSDT_NV;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtXuatSu_SP;
    // End of variables declaration//GEN-END:variables
}
