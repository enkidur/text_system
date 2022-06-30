import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static Texi Texi_number_sh(ArrayList num) {
        int i=0, temp_3 = 0;
        Texi bus_ins_sh = null;

        System.out.println("택시 번호를 선택해 주세요.");
        Scanner scanner = new Scanner(System.in);
        temp_3 = scanner.nextInt();

        for (i = 0; i < num.size(); i++) {
            bus_ins_sh = (Texi) num.get(i);
            if (temp_3 == bus_ins_sh.TexNum) {
                break;
            }
        }
        return bus_ins_sh;
    }
    private static boolean Texi_number_check(ArrayList num, String s) {
        {
            boolean flag = true;
            int i = 0;
            Texi bus_ins_sh = null;

            for (i = 0; i < num.size(); i++) {
                bus_ins_sh = (Texi) num.get(i);
                if (Objects.equals(String.valueOf(bus_ins_sh.TexNum), s)) {
                    System.out.println("\n같은 번호가 있습니다. 추가 할수 없습니다.\n");
                    flag = false;
                    break;
                }
            }
            return flag;

        }
    }
    public static void main(String[] args) {
        int flag=0,i=0;

        String[] temp;
        String temp_2;
        int temp_3,temp_4 = 0;

        ArrayList num = new ArrayList();
        Texi texi_ins = null;
        while (flag !=6) {

            System.out.println("택시 클래스 모델링 v0.1\n");
            System.out.println("동작을 선택하십시오.\n");
            System.out.println("1. 택시 추가");
            System.out.println("2. 택시 상태 변경");
            System.out.println("3. 승객 수 변경(1.탑승/2.하차)");
            System.out.println("4. 속도 변경(1.증가/2.감소)");
            System.out.println("5. 택시 정보확인하기");
            System.out.println("6. 종료");

            Scanner scanner = new Scanner(System.in);
            flag = scanner.nextInt();

            switch (flag) {
                case 1 -> {
                    System.out.println("택시번호 입력해 주십시오.");
                    scanner = new Scanner(System.in);
                    temp = scanner.nextLine().split(" ");
                    if (Texi_number_check(num, temp[0]))
                    {
                        texi_ins = new Texi(Integer.parseInt(temp[0]));
                        num.add(texi_ins);
                    }
                }
                case 2-> {
                    texi_ins = Texi_number_sh(num); //택시 주소값 받음
                    System.out.println("다음을 선택후 값을 넣어주세요. \n1.차량 상태변경(운행중 -> Race,일반 -> Normal) (ex : 1 Normal) \n2.오일량 변경(숫자기입)(ex : 2 10)");
                    scanner = new Scanner(System.in);
                    temp = scanner.nextLine().split(" ");
                    texi_ins.TexiStateChange(Integer.parseInt(temp[0]),temp[1]);
                }
                case 3 ->{
                    texi_ins = Texi_number_sh(num); //택시 주소값 받음
                    System.out.println("다음을 선택하세요 (1.탑승 / 2.하차)\n 탑승시 승객 수를 입력후 거리를 입력하세요.(ex : 1 10 100 <단위:m>)");
                    scanner = new Scanner(System.in);
                    temp = scanner.nextLine().split(" ");
                    if(temp[0].equals("2"))
                        temp[1] = "0";
                    texi_ins.TexiPassenger_Change(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                }
                case 4-> {
                    texi_ins = Texi_number_sh(num); //택시 주소값 받음
                    System.out.println("속도 변경 (ex: 1 10) (1.증가 / 2.감소)");
                    scanner = new Scanner(System.in);
                    temp = scanner.nextLine().split(" ");
                    texi_ins.Speed_Change(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                }
                case 5->{
                    texi_ins = Texi_number_sh(num); //버스 주소값 받음
                    texi_ins.Businfo();
                }
                default -> {
                    System.out.println("잘못 입력 하셨습니다. 다시 입력해 주세요.");
                }
            }
        }
    }
}