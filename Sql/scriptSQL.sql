-- TẠO DATABASE
CREATE DATABASE PTUD_QuanLyKaraokeNice
GO

-- SỬ DỤNG DATABASE
USE PTUD_QuanLyKaraokeNice
GO

-- BẢNG TÀI KHOẢN
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE TaiKhoan (
	maTaiKhoan CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	matKhau VARCHAR(255) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	CONSTRAINT CHK_TaiKhoan_maTaiKhoan_ThoaMau CHECK (maTaiKhoan LIKE 'NV[0-9][0-9][0-9]'), -- Kiểm tra mã tài khoảng phải có dạng NVXXX
	CONSTRAINT CHK_TaiKhoan_matKhau_ItNhat8KyTu CHECK (matKhau LIKE '________%'), -- Kiểm tra mật khẩu phải có ít nhất 8 ký tự
	CONSTRAINT CHK_TaiKhoan_matKhau_ChuaKyTuHoa CHECK (matKhau LIKE '%[A-Z]%'), -- Kiểm tra mật khẩu phải chứ ký tự hoa
	CONSTRAINT CHK_TaiKhoan_matKhau_ChuaKyTuThuong CHECK (matKhau LIKE '%[a-z]%'), -- Kiểm tra mật khẩu phải chứ ký tự thường
	CONSTRAINT CHK_TaiKhoan_matKhau_ChuaKyTuSo CHECK (matKhau LIKE '%[0-9]%'), -- Kiểm tra mật khẩu phải chứ ký tự số
	CONSTRAINT CHK_TaiKhoan_matKhau_ChuaKyTuDacBiet CHECK (matKhau LIKE '%[^A-Za-z0-9]%') -- Kiểm tra mật khẩu phải chứ ký tự đặc biệt
)
GO

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT TaiKhoan
VALUES ('NV111', '11a1@11A')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM TaiKhoan

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE TaiKhoan

-- BẢNG NHÂN VIÊN
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE NhanVien (
	maNhanVien CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	hoTen NVARCHAR(55) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	cccd NVARCHAR(12) NOT NULL UNIQUE,
	soDienThoai NVARCHAR(10) NOT NULL UNIQUE,
	ngaySinh DATE NOT NULL,
	gioiTinh BIT NOT NULL,
	tinh NVARCHAR(15) NOT NULL,
	quan NVARCHAR(30) NOT NULL,
	phuong NVARCHAR(30) NOT NULL,
	diaChiCuThe NVARCHAR(55) NOT NULL,
	chucVu NVARCHAR(15) NOT NULL,
	luong FLOAT NOT NULL,
	maTaiKhoan CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL UNIQUE,
	trangThai NVARCHAR(20) NOT NULL,
	CONSTRAINT CHK_NhanVien_maNhanVien_ThoaMau CHECK (maNhanVien LIKE 'NV[0-9][0-9][0-9]'), -- Kiểm tra mã nhân viên có dạng: NVxxx
	CONSTRAINT CHK_NhanVien_cccd_Chua12ChuSo CHECK (cccd like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- Kiểm tra căn cước công dân phải là 12 ký tự số
	CONSTRAINT CHK_NhanVien_soDienThoai_Chua10ChuSo CHECK (soDienThoai like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- Kiểm tra số điện thoại phải là 10 ký tự số
	CONSTRAINT CHK_NhanVien_ngaySinh_BeHonHienTai CHECK (ngaySinh < GETDATE()), -- Kiểm tra ngày sinh <  ngày hiện tại
	CONSTRAINT CHK_NhanVien_ngaySinh_18Tuoi CHECK (YEAR(GETDATE()) - YEAR(ngaySinh) >= 18), -- Kiểm tra tuổi nhân viên phải >= 18
	CONSTRAINT CHK_NhanVien_chucVu CHECK (chucVu = N'Quản lý' or chucVu = N'Nhân viên'), -- Kiểm tra chức vụ nhân viên phải là Quản lý hoặc Nhân viên
	CONSTRAINT CHK_NhanVien_luong_LonHon0 CHECK (luong > 0), -- Kiểm tra lương nhân viên phải > 0
	CONSTRAINT CHK_NhanVien_trangThai CHECK (trangThai = N'Đang làm' or trangThai = N'Nghỉ làm'), -- Kiểm tra trạng thái phải là Đang làm hoặc Nghỉ làm
	CONSTRAINT CHK_NhanVien_maTaiKhoan CHECK (maTaiKhoan = maNhanVien), -- Kiểm tra mã tài khoản phải là mã nhân viên
	CONSTRAINT FK_NhanVien_TaiKhoan FOREIGN KEY (maTaiKhoan) REFERENCES TaiKhoan(maTaiKhoan)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT NhanVien
VALUES ('NV111', 'Nguyen Thanh Trung', '111111111111', '0111111111', '2004-9-24', 1, '', '', '', '', N'Quản lý', 10, 'NV111', N'Đang làm')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM NhanVien

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE NhanVien

-- BẢNG KHÁCH HÀNG
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE KhachHang (
	maKhachHang CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	hoTen NVARCHAR(55) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	cccd NVARCHAR(12) NOT NULL UNIQUE,
	ngaySinh DATE NOT NULL,
	gioiTinh BIT NOT NULL,
	soDienThoai NVARCHAR(10) NOT NULL UNIQUE,
	tinh NVARCHAR(15) NOT NULL,
	quan NVARCHAR(30) NOT NULL,
	phuong NVARCHAR(30) NOT NULL,
	diaChiCuThe NVARCHAR(55) NOT NULL,
	CONSTRAINT CHK_KhachHang_maKhachHang_ThoaMau CHECK (maKhachHang LIKE 'KH[0-9][0-9][0-9]'), -- Kiểm tra mã khách hàng có dạng: KHxxx
	CONSTRAINT CHK_KhachHang_cccd_Chua12ChuSo CHECK (cccd like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- Kiểm tra căn cước công dân phải là 12 ký tự số
	CONSTRAINT CHK_KhachHang_soDienThoai_Chua10ChuSo CHECK (soDienThoai like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'), -- Kiểm tra số điện thoại phải là 10 ký tự số
	CONSTRAINT CHK_KhachHang_ngaySinh_BeHonHienTai CHECK (ngaySinh < GETDATE()) -- Kiểm tra ngày sinh <  ngày hiện tại
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT KhachHang
VALUES ('KH111', 'Pham Thanh An', '111111111111', '2004-9-24', 1, '0111111111', '', '', '', '')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM KhachHang

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE KhachHang

-- BẢNG LOẠI PHÒNG
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE LoaiPhong (
	maLoai CHAR(4) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	tenLoai NVARCHAR(55) NOT NULL,
	CONSTRAINT CHK_LoaiPhong_maLoai_ThoaMau CHECK (maLoai LIKE 'L[0-9][0-9][0-9]'), -- Kiểm tra mã loại theo mẫu LXXX
	CONSTRAINT CHK_LoaiPhong_tenLoai_KhacRong CHECK (tenLoai LIKE '[^ ]%') -- Kiểm tra tên loại khác rỗng
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT LoaiPhong
VALUES ('L001', N'Phòng thường')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM LoaiPhong

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE LoaiPhong

-- BẢNG PHÒNG
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE Phong (
	maPhong CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	loaiPhong CHAR(4) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	soLuongKhach INT NOT NULL,
	trangThai NVARCHAR(15) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	CONSTRAINT CHK_Phong_maPhong_ThoaMau CHECK (maPhong LIKE '[0-9][0-9].[0-9][0-9]'), -- Kiểm tra mã phòng theo mẫu: XX.YY
	CONSTRAINT CHK_Phong_loaiPhong_ThoaMau CHECK (loaiPhong LIKE 'L[0-9][0-9][0-9]'), -- Kiểm tra loại phòng theo mẫu LXXX
	CONSTRAINT CHK_Phong_soLuongKhach_oneOf CHECK (soLuongKhach IN (5, 10, 20)), -- Kiểm tra số lượng khách là 5, 10 hoặc 20 người
	CONSTRAINT CHK_Phong_trangThai_oneOf CHECK (trangThai IN ('TRONG', 'DANGTHUE', 'DADAT')), -- Kiểm tra trạng thái phòng phải là trống, đang thuê, đã đặt
	CONSTRAINT FK_Phong_LoaiPhong FOREIGN KEY (loaiPhong) REFERENCES LoaiPhong(maLoai)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT Phong
VALUES ('01.01', 'L001', 10, 'TRONG')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM Phong

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE Phong

-- BẢNG LOẠI DỊCH VỤ
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE LoaiDichVu (
	maLoaiDichVu char(6) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	tenLoaiDichVu nvarchar(55) NOT NULL,
	CONSTRAINT CHK_LoaiDichVu_maLoaiDichVu_ThoaMau check (maLoaiDichVu LIKE 'LDV[0-9][0-9][0-9]'), -- Kiểm tra mã loại dịch vụ theo mẫu: LDVXXX
	CONSTRAINT CHK_LoaiDichVu_tenLoaiDichVU_KhacRong check (tenLoaiDichVu LIKE '[^ ]%') -- Kiểm tra tên loại dịch vụ khác rỗng
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT LoaiDichVu
VALUES ('LDV001', N'Thức uống')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM LoaiDichVu

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE LoaiDichVu

-- BẢNG DỊCH VỤ
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE DichVu (
	maDichVu CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	tenDichVu NVARCHAR(55) NOT NULL,
	soLuong INT NOT NULL,
	donViTinh NVARCHAR(15) NOT NULL,
	loaiDichVu CHAR(6) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	giaMua FLOAT,
	CONSTRAINT CHK_DichVu_maDichVu_ThoaMau check (maDichVu LIKE 'DV[0-9][0-9][0-9]'), -- Kiểm tra mã dịch vụ theo mẫu: DVXXX
	CONSTRAINT CHK_DichVu_tenDichVu_KhacRong check (tenDichVu LIKE '[^ ]%'), -- Kiểm tra tên dịch vụ khác rỗng
	CONSTRAINT CHK_DichVu_soLuong_LonHonBang0 check (soLuong >= 0), -- Kiểm tra số lượng dịch vụ >= 0
	CONSTRAINT CHK_DichVu_donViTinh_KhacRong check (donViTinh LIKE '[^ ]%'), -- Kiểm tra đơn vị tính khác rỗng
	CONSTRAINT CHK_DichVu_loaiDichVu_TheoMau check (loaiDichVu LIKE 'LDV[0-9][0-9][0-9]'), -- Kiểm tra loại dịch vụ theo mẫu LDVXXX
	CONSTRAINT CHK_DichVu_giaMua_LonHon0 check (giaMua > 0), -- Kiểm tra giá mua > 0
	CONSTRAINT FK_DichVu_LoaiDichVu FOREIGN KEY (loaiDichVu) REFERENCES LoaiDichVu(maLoaiDichVu)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT DichVu
VALUES ('DV001', '7 Up', 10, N'Lon', 'LDV001', 20)

-- TRUY VẤN DỮ LIỆU
SELECT * FROM DichVu

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE DichVu

-- BẢNG ĐẶT PHÒNG
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE DatPhong (
	maDatPhong CHAR(7) COLLATE SQL_Latin1_General_CP1_CS_AS PRIMARY KEY,
	khachHang CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	nhanVien CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	ngayDatPhong DATE NOT NULL DEFAULT GETDATE(),
	gioDatPhong TIME(0) NOT NULL DEFAULT CONVERT(TIME(0), GETDATE()),
	ngayNhanPhong DATE NOT NULL,
	gioNhanPhong TIME(0) NOT NULL,
	trangThai NVARCHAR(15) NOT NULL,
	CONSTRAINT CHK_DatPhong_maDatPhong_ThoaMau CHECK (maDatPhong LIKE 'MDP[0-9][0-9][0-9][0-9]'), -- Kiểm tra mã đặt phòng theo mẫu: MDPXXXX
	CONSTRAINT CHK_DatPhong_khachHang_TheoMau CHECK (khachHang LIKE 'KH[0-9][0-9][0-9]'), -- Kiểm tra khách hàng theo mẫu: KHXXX
	CONSTRAINT CHK_DatPhong_nhanVien_TheoMau CHECK (nhanVien Like 'NV[0-9][0-9][0-9]'), -- Kiểm tra nhân viên theo mẫu: NVXXX
	CONSTRAINT CHK_DatPhong_ngayNhanPhong_KhongTruocHienTai CHECK (ngayNhanPhong >= CONVERT(DATE, GETDATE())), -- Kiểm tra ngày nhận phòng phải >= ngày hiện tại
	CONSTRAINT CHK_DatPhong_gioNhanPhong_TrongGioHoatDong CHECK (gioNhanPhong >= '7:0:0' AND gioNhanPhong <= '23:0:0'), -- Kiểm tra giờ nhận phòng phải trong giờ hoạt động và trước giờ đóng cửa (7h-23h)
	CONSTRAINT CHK_DatPhong_gioNhanPhong_LonHonBangHienTai CHECK (gioNhanPhong >= CONVERT(TIME(0), GETDATE())), -- Kiểm tra giờ nhận phòng >= giờ hiện tại
	CONSTRAINT CHK_DatPhong_gioNhanPhong_LonHonBangGioDatPhong CHECK (gioNhanPhong >= gioDatPhong), -- Kiểm tra giờ nhận phòng >= giờ đặt phòng
	CONSTRAINT CHK_DatPhong_trangThai CHECK (trangThai IN ('DaHuyDatPhong', 'DangCho', 'DangThuePhong', 'DaTraPhong')), -- Kiểm tra trạng thái đặt phòng phải là: Đã hủy đặt phòng, đang chờ, đang thuê phòng, đã trả phòng
	CONSTRAINT FK_DatPhong_KhachHang FOREIGN KEY (khachHang) REFERENCES KhachHang(maKhachHang),
	CONSTRAINT FK_DatPhong_NhanVien FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNhanVien)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT DatPhong
VALUES ('MDP1111', 'KH111', 'NV111', GETDATE(), CONVERT(TIME(0), GETDATE()), GETDATE(), CONVERT(TIME(0), GETDATE()), 'DangThuePhong')

-- TRUY VẤN DỮ LIỆU
SELECT * FROM DatPhong

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE DatPhong

-- BẢNG CHI TIẾT DỊCH VỤ
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE ChiTietDichVu (
	dichVu CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	datPhong CHAR(7) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	soLuong INT NOT NULL,
	PRIMARY KEY(dichVu, datPhong),
	CONSTRAINT CHK_DatPhong_dichVu_ThoaMau CHECK (dichVu LIKE 'DV[0-9][0-9][0-9]'), -- Kiểm tra dịch vụ phải theo mẫu: MDPXXXX
	CONSTRAINT CHK_DatPhong_datPhong_TheoMau CHECK (datPhong LIKE 'MDP[0-9][0-9][0-9][0-9]'), -- Kiểm tra đặt phòng theo mẫu: MDPXXXX
	CONSTRAINT CHK_DatPhong_soLuong_LonHon0 CHECK (soLuong > 0), -- Kiểm tra số lượng > 0
	CONSTRAINT FK_ChiTietDichVu_DichVu FOREIGN KEY (dichVu) REFERENCES DichVu(maDichVu),
	CONSTRAINT FK_ChiTietDichVu_DatPhong FOREIGN KEY (datPhong) REFERENCES DatPhong(maDatPhong)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT ChiTietDichVu
VALUES ('DV001', '01.01', 12)

-- TRUY VẤN DỮ LIỆU
SELECT * FROM ChiTietDichVu

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE ChiTietDichVu

-- TẠO FUNCTION LẤY GIỜ NHẬN PHÒNG THEO MÃ ĐẶT PHÒNG
GO
CREATE FUNCTION fnGetGioNhanPhongTheoMaDatPhong(@maDatPhong CHAR(7))
	RETURNS TIME(0)
	AS
		BEGIN
			RETURN (SELECT [gioNhanPhong] FROM [dbo].[DatPhong] WHERE [maDatPhong] = @maDatPhong)
		END;
GO

-- BẢNG Chi tiết đặt phòng
-- TẠO BẢNG VÀ RÀNG BUỘC
CREATE TABLE ChiTietDatPhong (
	datPhong CHAR(7) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	phong CHAR(5) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	gioVao TIME(0) NOT NULL,
	gioRa TIME(0),
	PRIMARY KEY(datPhong, phong),
	CONSTRAINT CHK_ChiTietDatPhong_datPhong_TheoMau CHECK (datPhong LIKE 'MDP[0-9][0-9][0-9][0-9]'), -- Kiểm tra đặt phòng theo mẫu: DVXXX
	CONSTRAINT CHK_ChiTietDatPhong_phong_ThoaMau CHECK (phong LIKE '[0-9][0-9].[0-9][0-9]'), -- Kiểm tra phòng theo mẫu: XX.YY
	CONSTRAINT CHK_ChiTietDatPhong_gioVao_LonHonBangGioNhanPhong CHECK (gioVao >= [dbo].[fnGetGioNhanPhongTheoMaDatPhong](datPhong)), -- Kiểm tra giờ vào >= giờ nhận phòng
	CONSTRAINT CHK_ChiTietDatPhong_gioVao_BeHonBangHienTai CHECK (gioVao <= convert(time(0), getdate())), -- Kiểm tra giờ vào <= giờ hiện tại
	CONSTRAINT CHK_ChiTietDatPhong_gioVao_LonHonBangGioMoCua CHECK (gioVao >= '7:0:0'), -- Kiểm tra giờ vào >= giờ mở cửa
	CONSTRAINT CHK_ChiTietDatPhong_gioVao_BeHonBang23h CHECK (gioVao <= '23:0:0'), -- Kiểm tra giờ vào <= 23h
	CONSTRAINT CHK_ChiTietDatPhong_gioRa_LonHonGioVao CHECK (gioRa > gioVao), -- Kiểm tra giờ ra > giờ vào
	CONSTRAINT CHK_ChiTietDatPhong_gioRa_BeHon24h CHECK (gioRa <= '23:59:59'), -- Kiểm tra giờ ra < 24h
	CONSTRAINT FK_ChiTietDatPhong_DatPhong FOREIGN KEY (datPhong) REFERENCES DatPhong(maDatPhong),
	CONSTRAINT FK_ChiTietDatPhong_Phong FOREIGN KEY (phong) REFERENCES Phong(maPhong)
)

-- THÊM DỮ LIỆU VÀO BẢNG
INSERT ChiTietDatPhong
VALUES ('MDP1111', '01.01', CONVERT(TIME(0), '22:05:00'), CONVERT(TIME(0), '23:50:00'))

-- TRUY VẤN DỮ LIỆU
SELECT * FROM ChiTietDatPhong

-- XÓA TẤT CẢ DỮ LIỆU KHỎI BẢNG
-- DELETE ChiTietDatPhong