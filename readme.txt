Cát Huy Thành - FX01399
Môn PRO192x
Assignment 4: Quản lý giảng viên nhân viên trường đại học X.
JDK version: 8 (1.8.0_212)
IDE: eclipse(ver 4.10)

1. Cài đặt chương trình:
	- Chạy công cụ eclipse.
	- Import file project (File -> Open Projects from file System)
2. Chạy chương trình:
	- Tab Project Explorer -> file project asignment4_PRO192x_cathuythanh -> file src -> pagkage ui, run file PRO192xA4.java
	- Load dữ liệu từ file data.txt (mặc định cùng thư mục dự án)
		Nếu file data.txt không tồn tại hoặc lưu ở nơi khác sẽ cho phép người dùng nhập đường dẫn đến file.
		Thông tin về đường dẫn file data.txt được lưu trữ trong file info.txt.
	- Menu: 1: Add staff: Thêm nhân viên. Thông tin nhân viên thêm vào được lưu trong file (data.txt).
												Thêm trường Id: phân biệt các nhân viên ( ID duy nhất không trùng lặp ).
					2: Search staff by name: Tìm nhân viên theo tên.
					3: Search staff by department/faculty: Tìm nhân viên theo phòng/khoa.
					4: Display all staff: Hiển thị danh sách nhân viên.
					5: Edit: Sửa thông tin nhân viên.
						1: Edit by ID: Sửa theo ID > nhập Id nhân viên cần sửa > nhập thông tin trường.
						2: Edit by name: Sửa theo tên > nhập tên nhân viên cần sửa:
							- Nếu có nhiều hơn 1 nhân viên có cùng tên với tên vưà nhập > nhập id theo list các nhân viên và tiến hành sửa thông tin.
							- Nếu chỉ có 1 nhân viên mang tên trùng với tên đã nhập > sửa > finish.
							Kết thúc quá trình sửa thông tin nhân viên sẽ được cập nhật trong file (data.txt).
							Nếu ID hoặc name không tồn tại sẽ hiển thị thông báo tương ứng.
					6. Exit.
					7. trọng sửa code
3. Thực thi Junit Test cho 2 nghiệp vụ cơ bản của chương trình
	- Tính lương - Staff/Teacher getSalary(): GetSalaryTest.java
	- Tìm kiếm nhân viên: EmployeeManagement.searchByName (SearchByNameTest.java)
												EmployeeManagement.searchByDept (SearchByDeptTest.java)
