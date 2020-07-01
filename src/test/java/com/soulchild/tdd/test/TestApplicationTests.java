package com.soulchild.tdd.test;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

//Junit 5.x
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//Junit 4.x
//import static org.junit.Assert.*;

//@SpringBootTest
@DisplayName("ALL TEST")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class TestApplicationTests {
    String track = "test";
    String test = "1111";

    @DisplayName("전체 검증")
    @Test
    public void contextLoads() {
        //여러 검증을 묶어서 처리 할수 있다
       assertAll(
                ()->assumeTrue(()->System.getProperty("os.name").startsWith("Mac")),
                ()->assertEquals("1111" , test),
                ()->assertNotNull(track),
                ()->assertThrows(ArithmeticException.class, ()->divide(100 , 0)),
                ()->assumingThat(track.equals("test"), ()->System.out.println("같다!!!!")),
                ()->assumingThat(track.equals("test"), ()->assertEquals(1,1))
        );
    }

    @DisplayName("null 체크")
    @Test
    public void test1() {
        //null check
        assertNotNull(track);
    }

    @DisplayName("동일한지 검증")
    @Test
    public void test2() {
        //두객체 비교 같은 경우 pass 기대값 : 실제값
        assertEquals("1111" , test);
    }

    @DisplayName("오류 검증")
    @Test
    public void test3() {
        //특정 익셉션이 발생하는지 체크
        assertThrows(ArithmeticException.class, ()->divide(100 , 0));
    }

    //환경에 따라 테스트가 불가할때 테스트 대상에서 제외시킴
    @Disabled("테스트 환경 구성 필요-")
    @Test
    public void anyTest() {
        System.out.println("anyTest");
    }

    @DisplayName("특정 환경별 검증-->OS")
    @Test
    public void test4() {
        //os 가 linux 인 경우 아래 검증 실행 Windows,Linux,Mac
        //Assumptions.assumeTrue()는 인자로 전달받은 값이 true이면 이후 테스트를 진행하고, 그렇지 않으면 테스트를 생략한다.
        //assumeTrue()에 전달한 값이 false인 경우 테스트를 생략하는 것이지 해당 테스트가 실패하는 것은 아니다.
        assumeTrue(System.getProperty("os.name").startsWith("Mac"));
        assumeTrue(()->System.getProperty("os.name").startsWith("Mac"));
        assertEquals(1,1);
    }

    @DisplayName("첫 번째 인자의 값이 true이면, 두 번째 인자로 받은 검증을 수행한다.")
    @Test
    public void test5() {
        //assumingThat()의 첫 번째 인자의 값이 true이면, 두 번째 인자로 받은 검증을 수행한다.
        assumingThat(track.equals("test"), ()->System.out.println("같다!!!!"));
        assumingThat(track.equals("test"), ()->assertEquals(1,1));
    }

    //@Tag를 사용하면 테스트 클래스나 메서드에 태그를 달 수 있다.
    //@Nested 중첩 구조를 만들수 있다.



    private int divide(int op1, int op2) {
        return op1 / op2;
    }

}
