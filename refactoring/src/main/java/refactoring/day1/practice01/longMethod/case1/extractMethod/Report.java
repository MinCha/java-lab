package refactoring.day1.practice01.longMethod.case1.extractMethod;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Report {
  public static void report(Writer out, List<Machine> machines, Robot robot) throws IOException {
    out.write("< FACTORY REPORT >\n");

    writeMarchine(out, machines);
    writeRobot(out, robot);

    out.write("====================\n");
  }

  private static void writeRobot(Writer out, Robot robot) throws IOException {
    out.write("Robot");
    if (robot.location() != null) out.write(" location=" + robot.location().name());
    if (robot.bin() != null) out.write(" bin=" + robot.bin());
    nextLine(out);
  }

  private static void writeMarchine(Writer out, List<Machine> machines) throws IOException {
    Iterator<Machine> line = machines.iterator();
    while (line.hasNext()) {
      Machine machine = (Machine) line.next();
      out.write("Machine : " + machine.name());
      if (machine.bin() != null) out.write(" bin=" + machine.bin());
      nextLine(out);
    }
    nextLine(out);
  }

  private static void nextLine(Writer out) throws IOException {
    out.write("\n");
  }


  // 이하 main method 는 Client code로 가정합니다.
  public static void main(String[] args) throws IOException {
    Robot robot = new Robot();

    Machine machineA = new Machine("Molding Machine", "Partition A");
    Machine machineB = new Machine("Armbelt Machine", "Partition B");

    List<Machine> machines = new ArrayList<Machine>(); // 공장의 자동기계 목록
    machines.add(machineA);
    machines.add(machineB);

    Writer writer = new OutputStreamWriter(System.out);

    machineA.put("molded bolt"); // 완성된 볼트
    robot.moveTo(machineA); // 로봇 이동
    report(writer, machines, robot);

    robot.pick(); // 볼트 pick
    report(writer, machines, robot);

    robot.moveTo(machineB); // 로봇 이동
    report(writer, machines, robot);

    robot.release(); // 볼트 release
    report(writer, machines, robot);

    writer.flush();

  }
}
