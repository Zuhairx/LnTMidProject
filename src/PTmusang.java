import java.util.Scanner;
import java.util.Vector;


// membuat aplikasi untuk mengolah seluruh data karyawan
public class PTmusang {
	Scanner sc;
	String kodeKaryawan,namakaryawan,jeniskelamin,jabatankaryawan;
	String kode ="^[A-Z]{2}-[0-9]{4}$";
	int gajikaryawan = 0;
	int jumlahmanager=0;
	int jumlahsupervisor=0;
	int jumlahadmin=0;
	Vector<data_PTMusang> data = new Vector<>();

	public PTmusang() {
		sc = new Scanner(System.in);
	}
	int scanInt() {
		int input = 0;
		try {
			input = sc.nextInt();
		} catch (Exception e) {
			System.out.println("harap masukkan angka");
			input = Integer.MIN_VALUE;
		}finally {
			sc.nextLine();
			return input;
		}
	}
	void run() {
		int input = 0;
		do {
			menuprint();
			input = scanInt();
			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				view();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			}
		}while (input != 0); System.out.println("Terima Kasih!");
	}
	void menuprint() {
		System.out.println("=======================");
		System.out.println("|      PT musang      |");
		System.out.println("=======================");
		System.out.println();
		System.out.println("1. insert data Karyawan");
		System.out.println("2. view data karyawan");
		System.out.println("3. update data karyawan");
		System.out.println("4. delete data karyawan");
		System.out.println("0. Exit");
		System.out.print("Choose >> ");
	}
	void insert() {
		do {
			System.out.print("input kode karyawan [MM-XXXX] : ");
			kodeKaryawan = sc.nextLine();
		}while(!(kodeKaryawan.matches(kode)));
		do {
			System.out.print("input nama karyawan [>= 3 ] : ");
			namakaryawan = sc.nextLine();
		}while(!(namakaryawan.length() >= 3));
		do {
			System.out.print("input jenis kelamin [Laki-laki|Perempuan] (Case Sensitive) : ");
			jeniskelamin = sc.nextLine();
		}while(!(jeniskelamin.equals("Laki-laki") || jeniskelamin.equals("Perempuan")));
		do {
			System.out.print("input jabatan [Manager|Supervisor|Admin] (Case Sensitive): ");
			jabatankaryawan = sc.nextLine();
			if(jabatankaryawan.equals("Manager")) {
				jumlahmanager++;
			}
			else if(jabatankaryawan.equals("Supervisor")) {
				jumlahsupervisor++;
			}
			else if(jabatankaryawan.equals("Admin")) {
				jumlahadmin++;
			}
		}while(!(jabatankaryawan.equals("Manager") || jabatankaryawan.equals("Supervisor")|| jabatankaryawan.equals("Admin")));
		do {
			System.out.print("input gaji karyawan [Manager = 8000000 | Supervisor = 6000000 | Admin = 4000000]: Rp. ");
			gajikaryawan = sc.nextInt();
		}while(!(jabatankaryawan.equals("Manager") && gajikaryawan == 8000000 || 
				jabatankaryawan.equals("Supervisor") && gajikaryawan == 6000000 ||
				jabatankaryawan.equals("Admin") && gajikaryawan == 4000000));

		data_PTMusang d = new data_PTMusang();
		d.listkodekaryawan = kodeKaryawan;
		d.listnamakaryawan = namakaryawan;
		d.listgenderkaryawan = jeniskelamin;
		d.listjabatankaryawan = jabatankaryawan;
		d.listgajikaryawan = gajikaryawan;
		data.add(d);
		if(jumlahmanager > 3 && jumlahmanager % 3 == 1) {
			perubahangajimanager();
		}
		if(jumlahsupervisor > 3 && jumlahsupervisor % 3 == 1) {
			perubahangajisupervisor();
		}
		if(jumlahadmin > 3 && jumlahadmin % 3 == 1) {
			perubahangajiadmin();
		}
		System.out.println("berhasil menambahkan karyawn dengan id " + d.listkodekaryawan);
		System.out.println("Enter to return");
		sc.nextLine();
		sc.nextLine();
	}
	void perubahangajimanager() {
		int a; 
		boolean aa = false;

		if(jumlahmanager > 3 && jumlahmanager % 3 == 1 && aa == false) {
			a = (data.size() - jumlahmanager % 3);
			for(int i=0;i<a;i++) {
				if(data.get(i).getJabatan().equals("Manager")) {
					gajikaryawan = data.get(i).getGaji() + (data.get(i).getGaji() * 1/10);
					data.get(i).setGaji(gajikaryawan);
				}
			}
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id "); 
			for(int i=0;i<a;i++) {
				if(data.get(i).getJabatan().equals("Manager")){
					System.out.print(data.get(i).getKodekaryawan());
					if(i<a-1 && data.get(i).getJabatan().equals("Manager")){
						System.out.print(", ");
					}
				}	
			}
			System.out.println("");
			aa = true;
		}
		if(aa == true){
			if(jumlahmanager % 3 == 1) {
				aa = true;
			}
			else {
				aa = false;
			}
		}
	}
	void perubahangajisupervisor() {
		int b;
		boolean bb = false;
		if(jumlahsupervisor > 3 && jumlahsupervisor % 3 == 1 && bb== false) {
			b = (data.size() - jumlahsupervisor % 3);
			for(int i=0;i<b;i++) {
				if(data.get(i).getJabatan().equals("Supervisor")) {
					gajikaryawan = data.get(i).getGaji() + (data.get(i).getGaji() * 3/40);
					data.get(i).setGaji(gajikaryawan);
				}
			}
			System.out.print("Bonus sebesar 7,5% telah diberikan kepada karyawan dengan id "); 
			for(int i=0;i<b;i++) {
				if(data.get(i).getJabatan().equals("Supervisor")) {
					System.out.print(data.get(i).getKodekaryawan());
					if(i<b-1 && data.get(i).getJabatan().equals("Supervisor")) {
						System.out.print(", ");
					}
				}

			}
			System.out.println("");
			bb = true;
		}
		if(bb == true){
			if(jumlahsupervisor % 3 == 1) {
				bb = true;
			}
			else {
				bb = false;
			}
		}
	}
	void perubahangajiadmin() {
		int c;
		boolean cc = false;
		if(jumlahadmin > 3 && jumlahadmin % 3 == 1 && cc== false) {
			c = (data.size() - jumlahadmin % 3);
			for(int i=0;i<c;i++) {
				if(data.get(i).getJabatan().equals("Admin")) {
					gajikaryawan = data.get(i).getGaji() + (data.get(i).getGaji() * 3/40);
					data.get(i).setGaji(gajikaryawan);
				}
			}
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id "); 
			for(int i=0;i<c;i++) {
				if(data.get(i).getJabatan().equals("Admin")) {
					System.out.print(data.get(i).getKodekaryawan());
					if(i<c-1 && data.get(i).getJabatan().equals("Admin")) {
						System.out.print(", ");
					}
				}

			}
			System.out.println("");
			cc = true;
		}
		if(cc == true){
			if(jumlahadmin % 3 == 1) {
				cc = true;
			}
			else {
				cc = false;
			}
		}
	}
	void sortascending() {
		data_PTMusang d;
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.size(); j++) {
				if (data.get(i).listnamakaryawan.compareTo(data.get(j).listnamakaryawan)<0) {
					d = data.get(j);
					data.set(j, data.get(i));
					data.set(i, d);
				}
			}
		}
	}
	void view() {
		if(data.isEmpty()) {
			System.out.println("No data!");
			System.out.println("Enter to return");
			sc.nextLine();
		}
		else {
			sortascending();
			System.out.println("================================================================================");
			System.out.printf("| %1s|%1s| %-20s |   %1s   |   %1s  |   %1s   |\n","No.", "Kode Karyawan", "Nama","Gender","jabatan","gaji");
			System.out.println("================================================================================");
			for (int i = 0; i< data.size(); i++) {
				System.out.printf("| %2s |%-13S|%-22s| %-10s | %-10s | %-9d|\n",i+1, data.get(i).listkodekaryawan,data.get(i).listnamakaryawan,
						data.get(i).listgenderkaryawan,data.get(i).listjabatankaryawan,data.get(i).listgajikaryawan);
			}System.out.println("================================================================================");	
		}
	}

	void update() {
		int update;
		data_PTMusang d = new data_PTMusang();
		if(data.isEmpty()) {
			System.out.println("No data!");
			System.out.println("Enter to return");
			sc.nextLine();
		}else {
			do {
				view();
				System.out.print("input nomor urutan karyawan yang ingin diupdate: ");
				update = sc.nextInt();
				sc.nextLine();
			}while(update <0 || update > data.size());
			if(update <= data.size() && update != 0) {
				update-=1;
				do {
					System.out.print("input kode karyawan [MM-XXXX] : ");
					kodeKaryawan = sc.nextLine();
				}while(!(kodeKaryawan.matches(kode)));
				do {
					System.out.print("input nama karyawan [>= 3 ] : ");
					namakaryawan = sc.nextLine();
				}while(!(namakaryawan.length() >= 3));
				do {
					System.out.print("input jenis kelamin [Laki-laki|Perempuan] (Case Sensitive) : ");
					jeniskelamin = sc.nextLine();
				}while(!(jeniskelamin.equals("Laki-laki") || jeniskelamin.equals("Perempuan")));
				do {
					System.out.print("input jabatan [Manager|Supervisor|Admin] (Case Sensitive): ");
					jabatankaryawan = sc.nextLine();
				}while(!(jabatankaryawan.equals("Manager") || jabatankaryawan.equals("Supervisor")|| jabatankaryawan.equals("Admin")));
				do {
					System.out.print("input gaji karyawan [Manager = 8000000 | Supervisor = 6000000 | Admin = 4000000]: Rp. ");
					gajikaryawan = sc.nextInt();
				}while(!(jabatankaryawan.equals("Manager") && gajikaryawan == 8000000 || 
						jabatankaryawan.equals("Supervisor") && gajikaryawan == 6000000 ||
						jabatankaryawan.equals("Admin") && gajikaryawan == 4000000));
				//
				if(jabatankaryawan.equals("Manager")){
					if(data.get(update).getJabatan().equals("Supervisor")){
						jumlahmanager++;
						jumlahsupervisor--;
					}
					else if(data.get(update).getJabatan().equals("Admin")){
						jumlahmanager++;
						jumlahadmin--;
					}
				}

				else if(jabatankaryawan.equals("Supervisor")) {
					if(data.get(update).getJabatan().equals("Admin")) {
						jumlahsupervisor++;
						jumlahadmin--;
					}
					else if(data.get(update).getJabatan().equals("Manager")) {
						jumlahsupervisor++;
						jumlahmanager--;
					}
				}
				else if(jabatankaryawan.equals("Admin")){
					if(data.get(update).getJabatan().equals("Supervisor")) {
						jumlahadmin++;
						jumlahsupervisor--;
					}
					else if(data.get(update).getJabatan().equals("Manager")) {
						jumlahadmin++;
						jumlahmanager--;
					}
				}
				data.get(update).setJabatan(jabatankaryawan);
				//
				d.listnamakaryawan = namakaryawan;
				d.listkodekaryawan = kodeKaryawan;
				d.listgenderkaryawan = jeniskelamin;
				d.listnamakaryawan = namakaryawan;
				d.listgenderkaryawan = jeniskelamin;
				d.listjabatankaryawan = jabatankaryawan;
				d.listgajikaryawan = gajikaryawan;
				data.add(d);
				for(int i = 1 ; i <= data.size(); i++) {
					System.out.println("berhasil mengupdate karyawan dengan id " + data.get(update).listkodekaryawan);
					data.remove(update);
					break;
				}
			}else{
				System.out.println("canceled");
			}System.out.println("Enter to return");
			sc.nextLine();
		}
	}
	void delete () {
		int delete;
		if(data.isEmpty()) {
			System.out.println("No data!");
			System.out.println("Enter to return");
			sc.nextLine();
		}else {
			do {
				view();
				System.out.println("input nomor urutan karyawan yang ingin dihapus: ");
				delete = sc.nextInt();
			}while(delete <0 || delete > data.size());
			if(delete <= data.size() && delete != 0) {
				delete-=1;
				for(int i = delete ; i <= data.size(); i++) {
					System.out.println("berhasil menghapus karyawan dengan id " + data.get(delete).listkodekaryawan);
					if(data.get(delete).listjabatankaryawan.equals("Manager")) {
						jumlahmanager--;
						data.remove(delete);
					}
					else if(data.get(delete).listjabatankaryawan.equals("Supervisor")) {
						jumlahsupervisor--;
						data.remove(delete);
					}
					else if(data.get(delete).listjabatankaryawan.equals("Admin")) {
						jumlahadmin--;
						data.remove(delete);
					}
					break;
				}
			}else{
				System.out.println("canceled");
			}System.out.println("Enter to return");
			sc.nextLine();
			sc.nextLine();
		}

	}



	public static void main(String[] args) {
		PTmusang main = new PTmusang();
		main.run();

	}

}
