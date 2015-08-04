package refactoring.day2;

import java.util.Date;

import refactoring.day2.client.CodeRefactoringExerciseClient;
import refactoring.day2.resource.Message;
import refactoring.day2.resource.Notifier;
import refactoring.day2.resource.Stop;

/**
 * 목표 - 아래 소스를 리팩터링 한다. - {@link CodeRefactoringExerciseClient}를 수정해도 좋다.
 * 
 * 제약사항 - boolean stop(Integer, String)의 반환타입, 파라메터타입은 바꾸지 않는다.
 * 
 * @author Min Cha
 * 
 * @deprecated
 */
public class CodeRefactoringExercise {
  private Stop cStop;
  private Stop rosStop;
  private Notifier noti;

  public CodeRefactoringExercise(Stop cStop, Stop rosStop, Notifier noti) {
    this.cStop = cStop;
    this.rosStop = rosStop;
    this.noti = noti;
  }

  public void stop(Integer t, String r) {
    if (t != null && (t == 1 || t == 2)) {
      if (r != null && r.length() > 0) {
        if (t == 1) {
          if (cStop.stop() == false) {
            throw new RuntimeException();
          }
        } else {
          if (rosStop.stop()) {
            Message m = new Message();
            m.setReason(r);
            m.setDate(new Date());
            if (noti.getServices().size() > 0) {
              if (noti.notify(m) == false) {
                throw new RuntimeException();
              }
            }
          } else {
            throw new RuntimeException();
          }
        }
      } else {
        throw new RuntimeException();
      }
    } else {
      throw new RuntimeException();
    }
  }
}
