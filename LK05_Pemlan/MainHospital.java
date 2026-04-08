/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hanif Maulana
 */
import java.util.Scanner;

public class MainHospital {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== HOSPITAL SIMULATION =====");
        
        //database simulasi 
        PatientProfileV1 patientV1 =
                new PatientProfileV1("P1", "Hamam", "11111111");

        PatientProfileV2 patientV2 =
                new PatientProfileV2("P2", "Rodho", "99999999", "Alergi Air");

        IntegrationGateway<PatientProfileV1> gatewayV1 = 
                new IntegrationGateway<>(patientV1);

        IntegrationGateway<PatientProfileV2> gatewayV2 =
                new IntegrationGateway<>(patientV2);

        boolean running = true;

        while (running) {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Patient V1");
            System.out.println("2. Patient V2");
            System.out.println("0. Exit");
            System.out.print("Pilih menu: ");

            int menu = input.nextInt();
            input.nextLine();

            if (menu == 0) {
                running = false;
                break;
            }

            System.out.print("Masukkan ID Pasien: ");
            String id = input.nextLine();

            System.out.println("Level Akses Dokter:");
            System.out.println("1 = Public");
            System.out.println("2 = Restricted");
            System.out.println("3 = Secret");
            System.out.print("Masukkan level akses dokter: ");

            int clearance = input.nextInt();
            input.nextLine();

            switch (menu) {

                case 1:
                    SecureResponse<PatientProfileV1> responseV1 =
                            gatewayV1.fetchData(id, clearance);

                    printV1(responseV1);
                    break;

                case 2:
                    SecureResponse<PatientProfileV2> responseV2 =
                            gatewayV2.fetchData(id, clearance);

                    printV2(responseV2);
                    break;

                default:
                    System.out.println("Menu tidak ditemukan.");
            }
        }

        System.out.println("Program selesai.");
        input.close();
    }

    //V1
    private static void printV1(SecureResponse<PatientProfileV1> response) {

        if (!response.isSuccess()) {
            System.out.println(response.getWarningMessage());
            return;
        }

        PatientProfileV1 data = response.getData();

        System.out.println("\n--- DATA PASIEN V1 ---");
        System.out.println("ID Pasien   : " + data.getPatientId());
        System.out.println("Nama        : " + data.getName());
        System.out.println("SSN         : " + data.getSsn());

        if (response.getWarningMessage() != null)
            System.out.println("Peringatan    : " + response.getWarningMessage());
    }

    //V2
    private static void printV2(SecureResponse<PatientProfileV2> response) {

        if (!response.isSuccess()) {
            System.out.println(response.getWarningMessage());
            return;
        }

        PatientProfileV2 data = response.getData();

        System.out.println("\n--- DATA PASIEN V2 ---");
        System.out.println("ID Pasien   : " + data.getPatientId());
        System.out.println("Nama        : " + data.getName());
        System.out.println("SSN         : " + data.getSsn());
        System.out.println("Alergi      : " + data.getAllergy());

        if (response.getWarningMessage() != null)
            System.out.println("Peringatan    : " + response.getWarningMessage());
    }
}