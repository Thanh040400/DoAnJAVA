Create DATABASE QLLinhKienPC_Laptop_java
USE QLLinhKienPC_Laptop_java
---

CREATE TABLE KHACHHANG(
MaKhachHang int identity(1,1),
Hoten [nvarchar](50) NULL,
GioiTinh [nvarchar](5) NULL,
SDT char(15) null,
DaXoa bit, -- xoa =1, chua xoa =0
primary key(MaKhachHang)
)
GO
--
CREATE TABLE LoaiLinhKien(
MaLoaiLinhKien int identity(1,1),
TenLoaiLinhKien nvarchar(50),
primary key(MaLoaiLinhKien)
)
GO
CREATE TABLE LinhKien(
MaLinhKien int identity(1,1),
TenLinhKien nvarchar(100),
XuatSu nvarchar(30),
GiaBan bigint,
BaoHanh nvarchar(20),
SoLuongTon int, --hiển thị đang còn hàng hay hết hàng
MaLoaiLinhKien int,
MaNCC int,
DaXoa bit, -- xoa =1, chua xoa =0
primary key(MaLinhKien)
)

GO
--
CREATE TABLE CHUCVU(
MaChucVu int identity(1,1) ,
TenChucVu [nvarchar](40) NULL,
PRIMARY KEY (MaChucVu),
)
GO
--
CREATE TABLE NHANVIEN(
MANV int identity(1,1),
HoTen nvarchar(50) NULL,
SDT char(15) null,
DiaCHi nvarchar(50) NULL,
NamSinh date NULL,
GioiTinh [nvarchar](5) NULL,
MaChucVu int ,
TaiKhoan char(50) NOT NULL,
MatKhau char(50) NOT NULL,
DaXoa bit, -- xoa =1, chua xoa =0
PRIMARY KEY(MANV)
)

GO
--

CREATE TABLE HOADON(
MaHoaDon int identity(1,1) ,
MaKhachHang int ,
MANV int,
NgayXuatHoaDon date,
Tongtien bigint,
DaXoa bit, -- xoa =1, chua xoa =0
PRIMARY KEY(MaHoaDon),
)
GO
--
CREATE TABLE CTHD(
MaHoaDon int ,
MaLinhKien int,
TenLinhKien nvarchar(100),
GiaBan bigint,
SoLuong int,
ThanhTien bigint
)
GO
--
CREATE TABLE NHACUNGCAP(
MaNCC int identity(1,1),
TenNCC nvarchar(50),
SDT char(15),
DiaChi nvarchar(50),
DaXoa bit, -- xoa =1, chua xoa =0
primary key(MANCC)
)
GO
--
CREATE TABLE PhieuNhapHang(
MaPNH int identity(1,1),
MANV int,
MaNCC int,
NgayNhapHang date,
TongTien bigint,
DaXoa bit, -- xoa =1, chua xoa =0
primary key(MaPNH)
)
GO
--
CREATE TABLE CTPNH(
MaCTPNH int identity(1,1),
MaPNH int,
MaLinhKien int,
LoaiLinhKien int,
TenLinhKien nvarchar(100),
XuatSu nvarchar(30),
GiaBan bigint,
BaoHanh nvarchar(20),
SoLuongNhap int,
ThanhTien bigint,
PRIMARY KEY(MaCTPNH)
)
GO
--tao khoa ngoai
alter table LinhKien add  foreign key(MaNCC) references NHACUNGCAP (MaNCC)on update no action on delete no action
Alter table LinhKien add  foreign key(MaLoaiLinhKien) references LoaiLinhKien (MaLoaiLinhKien)on update no action on delete no action


Alter table PhieuNhapHang add  foreign key(MaNCC) references NHACUNGCAP (MaNCC)on update no action on delete no action

Alter table PhieuNhapHang add  foreign key(MANV) references NHANVIEN (MANV)on update no action on delete no action
Alter table CTPNH add  foreign key(MAPNH) references PhieuNhapHang(MAPNH)on update no action on delete no action

Alter table CTPNH add  foreign key(MaLinhKien) references LinhKien(MaLinhKien)on update no action on delete no action

Alter table NHANVIEN add  foreign key(MaChucVu) references CHUCVU(MaChucVu)on update no action on delete no action

Alter table HOADON add  foreign key(MaKhachHang) references KHACHHANG(MaKhachHang)on update no action on delete no action

Alter table HOADON add  foreign key(MANV) references NHANVIEN(MANV) on update no action on delete no action

Alter table CTHD add  foreign key(MaHoaDon) references HOADON(MaHoaDon) on update no action on delete no action

Alter table CTHD add  foreign key(MaLinhKien) references LinhKien(MaLinhKien)on update no action on delete no action
GO
--nhap du lieu
insert into CHUCVU values(N'Nhân Viên')
insert into CHUCVU values(N'Quản Lý')

insert into NHACUNGCAP values(N'Kim Đồng','0357999999',N'123 Lâm Xuân, TP Thủ Đức',1)
insert into NHACUNGCAP values(N'Nhật Kiệu','035788888',N'98 Nguyễn Huệ, TP HÀ Nội',1)

insert into LoaiLinhKien values(N'Bàn Phím')
insert into LoaiLinhKien values(N'Chuột')
insert into LoaiLinhKien values(N'Màn Hình')

insert into LinhKien values(N'Bàn Phím Cơ 500',N'Nhật Bản',1000000,N'1 Năm',50,1,1,0)
insert into LinhKien values(N'Chuột Logitech pro',N'Trung Quốc',50000,N'1 Năm',100,2,1,0)
insert into LinhKien values(N'Màn Hình AOE 2K5',N'Hàn QUốc',9999999,N'1,5 Năm',100,3,2,0)
select *from KHACHHANG
insert into KHACHHANG values(N'Nguyễn Đình Hùng',N'Nam','035488888',0)
insert into KHACHHANG values(N'Nguyễn Văn Quang',N'Nam','055777777',0)
insert into KHACHHANG values(N'Đào Nhật Tín',N'Nam','055777777',0)
insert into KHACHHANG values(N'Đào Thiên Ngân',N'Nữ','078833333',0)

insert into NHANVIEN values(N'Nguyễn Trung Thành','022555488',N'Quận Cam-America','9/20/1962',N'Nam',2,'trandan','trandan',0)
insert into NHANVIEN values(N'Hàn Dao','08455444',N'Linh Sơn- Côn Lôn','8/10/1992',N'Nữ',1,'handao','handao',0)
insert into NHANVIEN values(N'Hạo Thiên','022555488',N'Hạo Thiên Tông','9/3/1999',N'Nam',1,'haothien','haothien',0)

insert into HOADON values(1,2,'4/11/2021',1000000,0)
insert into HOADON values(2,2,'4/15/2021',11099999,0)

insert into CTHD values(1,1,N'Bàn Phím Cơ 500',1000000,1,1000000)
insert into CTHD values(2,1,N'Bàn Phím Cơ 500',1000000,1,1000000)
insert into CTHD values(2,2,N'Chuột Logitech pro',50000,2,100000)
insert into CTHD values(2,3,N'Màn Hình AOE 2K5',9999999,1,9999999)

insert into PhieuNhapHang values(1,2,'10/10/2021',1054999900,0)

insert into CTPNH values(1,1,1,N'Bàn Phím Cơ 500',N'Nhật Bản',1000000,N'1 Năm',50,50000000)
insert into CTPNH values(1,2,2,N'Chuột Logitech pro',N'Trung Quốc',50000,N'1 Năm',100,5000000)
insert into CTPNH values(1,3,3,N'Màn Hình AOE 2K5',N'Hàn QUốc',9999999,N'1,5 Năm',100,999999900)

select * from NHANVIEN
select * from CHUCVU
select TenChucVu from  CHUCVU
select MaNV, HoTen, SDT ,DiaCHi ,NamSinh ,GioiTinh,b.TenChucVu ,TaiKhoan , MatKhau 
from NHANVIEN  a,CHUCVU b  
where DaXoa = 0 and a.MaChucVu=b.MaChucVu
select  GioiTinh
from NHANVIEN a, KHACHHANG b
where DaXoa=0 and a.GioiTinh =b.GioiTinh


SELECT <Danh sách các cột>
FROM <Danh sách Bảng>
WHERE <Các điều kiện>
GROUP BY <Tên cột>
HAVING <Điều kiện dựa trên GROUP BY>
ORDER BY <Danh sách cột>


select GioiTinh 
from NHANVIEN
where DaXoa=0
group by GioiTinh

order by GioiTinh

select MaHoaDon, NgayXuatHoaDon, Tongtien from HOADON