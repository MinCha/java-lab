package refactoring.day1.practice04.DivergentChange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import refactoring.day1.practice01.longMethod.case1.extractMethod.Robot;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionTest {
  @Mock
  private Robot robot;

  @Test(expected = IllegalArgumentException.class)
  public void testGetPrescription() throws Exception {
    Prescription prescription = new Prescription();
    assertEquals("two pill a day", prescription.getPrescription("adult", "serious"));
    assertEquals("one pill a day", prescription.getPrescription("adult", "mild"));
    assertEquals("one pill a day", prescription.getPrescription("child", "serious"));
    assertEquals("half pill a day", prescription.getPrescription("child", "mild"));

    prescription.getPrescription("children", "mild");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetIngestionPerDay() throws Exception {
    Prescription prescription = new Prescription();
    assertTrue(2d == prescription.getMaxIngestionPerDay("adult", "serious"));
    assertTrue(1d == prescription.getMaxIngestionPerDay("adult", "mild"));
    assertTrue(1d == prescription.getMaxIngestionPerDay("child", "serious"));
    assertTrue(0.5d == prescription.getMaxIngestionPerDay("child", "mild"));

    prescription.getPrescription("adlut", "mild");
  }

}
