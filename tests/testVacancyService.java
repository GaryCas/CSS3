import org.jcsp.util.Buffer;
import services.VacancyService;

public class testVacancyService {

//    public static void main(String args[]) {
//
//        testGetSize1();
//        testGetSize2();
//        testGetSize3();
//        testGetSize4();
//
//    }
//
//    // proves that the getBufferSize method works
//    public static void testGetSize1() {
//        // given
//        Buffer b = new Buffer(20);
//
//        for (int i = 0; i < 10; i++) {
//            b.put(i);
//        }
//
//        // when
//        int test = VacancyService.getBufferSize(b);
//
//        // then
//        assert (test == 10);
//        assert (b.getState() != 0);
//        System.out.println("testGetSize1() has run successfully");
//    }
//
//    // proves that the buffer being tested is emptied
//    public static void testGetSize2() {
//        // given
//        Buffer b = new Buffer(20);
//
//        for (int i = 0; i < 10; i++) {
//            b.put(i);
//        }
//
//        // when
//        VacancyService.getBufferSize(b);
//
//        // then
//        assert (b.getState() != 0);
//        System.out.println("testGetSize2() has run successfully");
//    }
//
//    // proves that the buffer being tested does not change it's size
//    public static void testGetSize3() {
//        // given
//        Buffer b = new Buffer(20);
//
//        for (int i = 0; i < 10; i++) {
//            b.put(i);
//        }
//
//        // when
//        int test1 = VacancyService.getBufferSize(b);
//
//        // then
//        assert (test1 == 10);
//
//        int test2 = VacancyService.getBufferSize(b);
//        assert(test2 == 10);
//
//        System.out.println("testGetSize3() has run successfully");
//    }
//
//    // proves that the buffer being tested does not change it's size
//    public static void testGetSize4() {
//        // given
//        VacancyService.customers = new Buffer(10);
//
//        for (int i = 0; i < 10; i++) {
//            VacancyService.customers.put(i);
//        }
//
//        // when
//        int test1 = VacancyService.getBufferSize(VacancyService.customers);
//
//        // then
//        assert (test1 == 10);
//
//        int test2 = VacancyService.getBufferSize(VacancyService.customers);
//        assert(test2 == 10);
//
//        System.out.println("testGetSize4() has run successfully");
//    }


}