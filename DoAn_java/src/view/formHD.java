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
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thanh
 */
public class formHD extends javax.swing.JInternalFrame {
    public Connection conn;
     public   PreparedStatement ps=null;
     public   ResultSet rs=null;
     DefaultTableModel dtmHD;
     DefaultTableModel dtmCTHD;
    /**
     * Creates new form formHD
     */
    public formHD() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI UI=(BasicInternalFrameUI)this.getUI();
        UI.setNorthPane(null);
        HienThiCboNhanVien();
        HienThiCboKhachHang();
        HienThiCboTenLinhKien();
        dtmCTHD=(DefaultTableModel) tblCTHD.getModel();
        dtmHD=(DefaultTableModel) tblHoaDon.getModel();
        hienThiTableHoaDon();
    }

     public Connection KetNoiCSDL(){
        String user="sa";
        String pass="123456";
        try{
            conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLLinhKienPC_Laptop_java",user,pass);
            
            return conn;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
     public void HienThiCboTenLinhKien(){
        try{
            conn=KetNoiCSDL();
            String sql="select TenLinhKien from LinhKien where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenLK.removeAllItems();
            while(rs.next()){
                cboTenLK.addItem(rs.getString("TenLinhKien"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboNhanVien(){
        try{
            conn=KetNoiCSDL();
            String sql="select HoTen from NHANVIEN where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenNV.removeAllItems();
            while(rs.next()){
                cboTenNV.addItem(rs.getString("HoTen"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboKhachHang(){
        try{
            conn=KetNoiCSDL();
            String sql="select HoTen from KHACHHANG where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenKH.removeAllItems();
            while(rs.next()){
                cboTenKH.addItem(rs.getString("HoTen"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private int LayMaKhachHangTheoTen(String TenKH){
        int Ma=0;
        try{
            //conn=KetNoiCSDL();
            String sql="select MaKhachHang from KHACHHANG where DaXoa=0 and Hoten=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenKH);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MaKhachHang");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private int LayMaNhanVienTheoTen(String TenNV){
        int Ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select MANV from NHANVIEN where DaXoa=0 and HoTen=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenNV);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MANV");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private int LayMaLinhKienTheoTen(String TenLinhKien){
        int Ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select MaLinhKien from LinhKien where TenLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenLinhKien);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MaLinhKien");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private void hienThiTableHoaDon(){
        try {
            dtmHD.setRowCount(0);
            conn=KetNoiCSDL();
            String sql="select MaHoaDon,b.Hoten,c.HoTen,NgayXuatHoaDon,Tongtien\n" +
                        "from HOADON a, KHACHHANG b, NHANVIEN c\n" +
                        "where a.DaXoa=0 and a.MaKhachHang=b.MaKhachHang and a.MANV=c.MANV";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("MaHoaDon"));
                vec.add(rs.getString("Hoten"));
                vec.add(rs.getString("HoTen"));
                vec.add(rs.getDate("NgayXuatHoaDon"));
                vec.add(rs.getLong("Tongtien"));
                dtmHD.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void hienThitableCTHDTheoMaHD(int maHD){
        try {
            conn=KetNoiCSDL();
            dtmCTHD.setRowCount(0);
            String sql="select TenLinhKien,GiaBan,SoLuong,ThanhTien from CTHD where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getString("TenLinhKien"));
                vec.add(rs.getLong("GiaBan"));
                vec.add(rs.getInt("SoLuong"));
                vec.add(rs.getLong("ThanhTien"));
                dtmCTHD.addRow(vec);
            }
        } catch (Exception e) {
        }
    }

    private void xoaHDTheoMaHD(int maHD){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set DaXoa=1 where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void xoaCTHD(int maHD, int maLK){
        try {
            conn=KetNoiCSDL();
            String sql="delete from CTHD where MaHoaDon=? and MaLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.setInt(2, maLK);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private long layTongTienSauKhiXoaHoaDon(int maHD){
        long tongtien=0;
        try {
            conn=KetNoiCSDL();
            String sql="select ThanhTien from CTHD where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                long thanhtien=rs.getLong("ThanhTien");
                tongtien+=thanhtien;
            }
            return tongtien;
        } catch (Exception e) {
        }
        return tongtien;
    }
    private void capNhatTongTienHoaDonSauKhiXoaCTHD(int maHD, long tongTien){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set Tongtien=? where MaHoaDon=?";
             ps=conn.prepareStatement(sql);
             ps.setLong(1, tongTien);
             ps.setInt(2, maHD);
             ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    void dongTextBox(){
        txtGiaBan.setText("");
        txtMaHD.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
        txtTongTien.setText("");
    }
    void dongTextBoxCTHD(){
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
    }
    private void capNhatHoaDon(int maHD, int maKH, int maNV){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set MaKhachHang=?, MANV=? where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maKH);
            ps.setInt(2, maNV);
            ps.setInt(3, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void capNhatCTHD(int maHD, int maLK, long giaBan, int soLuong, long thanhTien){
        try {
            conn=KetNoiCSDL();
            String sql="update CTHD set GiaBan=?,SoLuong=?, ThanhTien=? where MaHoaDon=? and MaLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setLong(1, giaBan);
            ps.setInt(2, soLuong);
            ps.setLong(3, thanhTien);
            ps.setInt(4, maHD);
            ps.setInt(5, maLK);
            ps.executeUpdate();
        } catch (Exception e) {
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

        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboTenKH = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtThanhTien = new javax.swing.JTextField();
        cboTenLK = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1396, 928));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel1.setText("Tìm Kiếm Nhanh:");

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
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnLuu)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel6.setText("HÓA ĐƠN");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblHoaDon.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Khách Hàng", "Nhân Viên", "Ngày Xuất HD", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(40);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblCTHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Linh Kiện", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        tblCTHD.setRowHeight(40);
        tblCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTHD);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setText("Mã Hóa Đơn");

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel4.setText("Khách Hàng");

        cboTenKH.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Nhân Viên");

        cboTenNV.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel5.setText("Tổng Tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(txtMaHD)
                        .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        cboTenLK.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel9.setText("Số Lượng");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel8.setText("Gía Bán");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel7.setText("Tên Linh Kiện");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel10.setText("Thành Tiền");

        txtGiaBan.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtGiaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiaBanMouseClicked(evt);
            }
        });

        txtSoLuong.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 16, Short.MAX_VALUE)))
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(212, 212, 212)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(552, 552, 552))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseClicked
        // TODO add your handling code here:
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtSoLuongMouseClicked

    private void txtGiaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaBanMouseClicked
        // TODO add your handling code here:
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtGiaBanMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try{
            int selectedRowHD=tblHoaDon.getSelectedRow();
            int selectedrowCTHD=tblCTHD.getSelectedRow();
            int maHD = Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
            int maLK = LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn dữ liệu để xóa");
            return;
        }
        int selectedRowHD=tblHoaDon.getSelectedRow();
        int selectedrowCTHD=tblCTHD.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
        int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());

        if(selectedRowHD!=-1 &&selectedrowCTHD!=-1){

            int result= JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa chi tiết hóa đơn này không?",
                "Thông báo", JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION){

                xoaCTHD(maHD, maLK);

                long tongtiensauKhiXoa=layTongTienSauKhiXoaHoaDon(maHD);
                capNhatTongTienHoaDonSauKhiXoaCTHD(maHD, tongtiensauKhiXoa);

                hienThiTableHoaDon();
                hienThitableCTHDTheoMaHD(maHD);
                dongTextBox();
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                return;
            }
        }
        else if(selectedRowHD!=-1){
            int result=JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa luôn hóa đơn "+maHD+" không?"
                , "Xóa Hóa Đơn"
                , JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION){
                xoaHDTheoMaHD(maHD);
                hienThiTableHoaDon();
                dongTextBox();

                return;
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        dongTextBoxCTHD();
        int row=tblHoaDon.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
        String tenkh=tblHoaDon.getValueAt(row, 1).toString();
        String tenNV=tblHoaDon.getValueAt(row, 2).toString();
        long tongTien=Long.parseLong(tblHoaDon.getValueAt(row, 4).toString());
        Locale VN = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(VN);
        NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
        txtTongTien.setText(VNDFormat.format(tongTien));
        txtMaHD.setText(tblHoaDon.getValueAt(row, 0).toString());
        cboTenKH.getModel().setSelectedItem(tenkh);
        cboTenNV.getModel().setSelectedItem(tenNV);
        hienThitableCTHDTheoMaHD(maHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try{
            int selectedRowHD=tblHoaDon.getSelectedRow();
            int selectedrowCTHD=tblCTHD.getSelectedRow();
            int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
            int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
            int maNV=LayMaNhanVienTheoTen(cboTenNV.getSelectedItem().toString());
            int maKH=LayMaKhachHangTheoTen(cboTenKH.getSelectedItem().toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu và thay đổi các giá trị ở textbox"
                + "hoặc combobox để lưu !");
            return;
        }

        int selectedRowHD=tblHoaDon.getSelectedRow();
        int selectedrowCTHD=tblCTHD.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
        int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
        int maNV=LayMaNhanVienTheoTen(cboTenNV.getSelectedItem().toString());
        int maKH=LayMaKhachHangTheoTen(cboTenKH.getSelectedItem().toString());

        if(selectedRowHD != -1 && selectedrowCTHD != -1){
            try {
                int soLuong=Integer.parseInt(txtSoLuong.getText());
                if(soLuong<0){
                    JOptionPane.showMessageDialog(rootPane, "Số lượng phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Số lượng phải là số");
                return;
            }

            try {
                long giaBan=Long.parseLong(txtGiaBan.getText());
                if(giaBan<0){
                    JOptionPane.showMessageDialog(rootPane, "Gía bán phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Giá bán phải là số");
                return;
            }
            int soLuong=Integer.parseInt(txtSoLuong.getText());
            Long giaBan=Long.parseLong(txtGiaBan.getText());
            long thanhTien=soLuong*giaBan;

            capNhatCTHD(maHD, maLK, giaBan, soLuong, thanhTien);
            Long tongtien=layTongTienSauKhiXoaHoaDon(maHD);
            capNhatTongTienHoaDonSauKhiXoaCTHD(maHD, tongtien);
            hienThiTableHoaDon();
            hienThitableCTHDTheoMaHD(maHD);
            dongTextBoxCTHD();
            txtTongTien.setText("");
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
            return;
        }
        else{
            capNhatHoaDon(maHD, maKH, maNV);
            hienThiTableHoaDon();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật hóa đơn thành công khách hàng và nhân viên cho hóa đơn "+maHD+" thành công");
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void tblCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseClicked
        // TODO add your handling code here:
        int row=tblCTHD.getSelectedRow();
        cboTenLK.getModel().setSelectedItem(tblCTHD.getValueAt(row, 0));
        txtGiaBan.setText(tblCTHD.getValueAt(row, 1).toString());
        txtSoLuong.setText(tblCTHD.getValueAt(row, 2).toString());
        long thanhtien=Long.parseLong(tblCTHD.getValueAt(row, 3).toString());
        Locale VN = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(VN);
        NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
        txtThanhTien.setText(VNDFormat.format(thanhtien));
    }//GEN-LAST:event_tblCTHDMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTenKH;
    private javax.swing.JComboBox<String> cboTenLK;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
