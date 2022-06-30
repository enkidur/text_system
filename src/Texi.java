import java.util.Scanner;

public class Texi {

    Integer TexNum;             // 택시번호
    int Oil_Volume = 100;       //주유량
    int Real_speed = 0;         // 현재속도
    String destination = "";      //목적지
    int Ori_Distance = 300;       //기본거리
    int Dest_Distance = 0;      //목적지까지의 거리
    int Fare = 0;               //요금
    int Ori_Fare = 3000;          //기본요금
    int Dest_Fare = 100;          //거리당요금 100m 100원
    String Situation = "Normal";  //상태 Normal = 일반, Race = 운행 중, 운행중일시 탑승 불가.

    int Max_passenger = 3;  //최대 승객
    int Real_passenger = 0; //현재 승객


    public Texi(Integer texNum) {
        TexNum = texNum;
    }

    boolean TexiOil_check() {
        boolean flag = true;
        if (Oil_Volume < 10) { // 주유량 떨어질때
            Situation = "Normal";
            Real_passenger = 0;
            Real_speed = 0;
            destination = "";
            Dest_Distance = 0;

            System.out.println("**********************************************");
            System.out.println("운행을 정지합니다. 남아있는 승객을 하차 시킵니다.");
            flag = false;
            System.out.println("주유량을 확인해 주세요.");
            System.out.println("**********************************************");
        }
        return flag;
    }

    public void TexiStateChange(int select_num, String val) {
        int temp = 0;
        switch (select_num) {
            case 1 -> {// 차량상태 변경
                try {
                    if (!Situation.equals(val))
                        Situation = val;
                    if (Situation.equals("Race"))
                        TexiOil_check();
                } catch (NumberFormatException e) {
                    System.out.println("값을 잘못 입력 하셨습니다.");

                }
            }
            case 2 -> { //오일량 변경
                temp = Integer.parseInt(val);
                if (Oil_Volume != temp) {
                    Oil_Volume = temp;
                }
                TexiOil_check();
            }
            default -> {
            }
        }
    }

    public void TexiPassenger_Change(int number, int person_num,int Distance) {

        switch (number) {
            case 1 -> {  // 탑승
                if (Situation.equals("Normal")) {//최대 승객수 구함
                    if (Max_passenger < Real_passenger + person_num) {
                        System.out.println("\n***** 최대 승객을 넘었습니다. 승차를 거부합니다. *****\n");
                    } else {
                        Real_passenger += person_num;
                        Dest_Distance = Distance;
                        if (Ori_Distance < Dest_Distance) {
                            Fare = Ori_Fare + (Dest_Distance - Ori_Distance) * Dest_Fare;
                        } else {
                            Fare = Ori_Fare;
                        }
                    }
                }
                else
                    System.out.println("\n***** 탑승불가 *****\n");
        }
            case 2 -> {  // 감소
                    Real_passenger = person_num;
                    Situation = "Normal";
                    Fare = 0;
            }
            default -> {
            }
        }
    }


    public void Speed_Change(int number, int speed_val) {
        if (TexiOil_check()) {
            switch (number) {
                case 1 -> {  // 속도증가
                    Real_speed += speed_val;
                }
                case 2 -> {  // 속도감소
                    if (Real_speed - speed_val < 0)
                        System.out.println("\n***** 0보다 작습니다. 변경하지 않습니다. *****\n");
                    else
                        Real_passenger -= speed_val;
                }
                default -> {
                }
            }
        }
    }

    public void Businfo() {
        String temp = "";
        System.out.println("**********************************************");
        System.out.println("택시번호: " + TexNum);
        System.out.println("현재승객수: " + Real_passenger);
        System.out.println("현재속도: " + Real_speed);
        System.out.println("주유량: " + Oil_Volume + ", 상태: " + Situation);
        System.out.println("**********************************************");
        if(Real_passenger >0) {
            System.out.println("결제 하시겠습니까? Y/N");
            Scanner scanner = new Scanner(System.in);
            temp = scanner.nextLine();
            if (temp.equals("Y") || temp.equals("y")) {
                Oil_Volume = 100;
                Real_speed = 0;
                destination = "";
                Dest_Distance = 0;
                Fare = 0;
                Situation = "Normal";
                Real_passenger = 0;
            }
        }
    }
}