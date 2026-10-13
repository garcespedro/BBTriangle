import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import triangle.Triangle;

import static org.junit.jupiter.api.Assertions.*;

public class TestTriangle {
    private static final String EQUILATERAL  = "equilateral";
    private static final String ISOSCELES    = "isosceles";
    private static final String RIGHT_ANGLED = "right-angled";
    private static final String SCALENE      = "scalene";
    private static final String IMPOSSIBLE   = "impossible";

    private static final int MAX = Integer.MAX_VALUE;
    private static final double DELTA = 0.001;

    // ============================================================
    // classify()  (29 testes: ECP1 a ECP5, BVA 2.2 e BVA 2.3)
    // ============================================================
    // ---- Impossível (ECP1 e BVA 2.3) ----
    @Test
    @DisplayName("Imp1: (12, 2, 3) s1 > s2 + s3 -> impossible")
    void imp1_s1MaiorQueSomaDosOutros() {
        Triangle triangle = new Triangle(12, 2, 3);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp2: (2, 12, 3) s2 > s1 + s3 -> impossible")
    void imp2_s2MaiorQueSomaDosOutros() {
        Triangle triangle = new Triangle(2, 12, 3);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp3: (3, 2, 12) s3 > s1 + s2 -> impossible")
    void imp3_s3MaiorQueSomaDosOutros() {
        Triangle triangle = new Triangle(3, 2, 12);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp4: (-1, 2, 2) lado negativo -> impossible")
    void imp4_ladoNegativo() {
        Triangle triangle = new Triangle(-1, 2, 2);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp5: (0, 2, 2) lado a zero (fronteira) -> impossible")
    void imp5_ladoZero() {
        Triangle triangle = new Triangle(0, 2, 2);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp6: (2, 3, 5) s3 = s1 + s2 (fronteira) -> impossible")
    void imp6_s3IgualASomaDosOutros() {
        Triangle triangle = new Triangle(2, 3, 5);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("Imp7: (2, 3, 6) s3 > s1 + s2 -> impossible")
    void imp7_s3AcimaDaFronteira() {
        Triangle triangle = new Triangle(2, 3, 6);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    // ---- 8 cantos do domínio (BVA 2.2) ----
    @Test
    @DisplayName("BVA1: (0, 0, 0) canto (0, 0, 0) -> impossible")
    void bva1_cantoMinimoMinimoMinimo() {
        Triangle triangle = new Triangle(0, 0, 0);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA2: (0, 0, Max) canto (0, 0, Max) -> impossible")
    void bva2_cantoMinimoMinimoMaximo() {
        Triangle triangle = new Triangle(0, 0, MAX);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA3: (0, Max, 0) canto (0, Max, 0) -> impossible")
    void bva3_cantoMinimoMaximoMinimo() {
        Triangle triangle = new Triangle(0, MAX, 0);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA4: (0, Max, Max) canto (0, Max, Max) -> impossible")
    void bva4_cantoMinimoMaximoMaximo() {
        Triangle triangle = new Triangle(0, MAX, MAX);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA5: (Max, 0, 0) canto (Max, 0, 0) -> impossible")
    void bva5_cantoMaximoMinimoMinimo() {
        Triangle triangle = new Triangle(MAX, 0, 0);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA6: (Max, 0, Max) canto (Max, 0, Max) -> impossible")
    void bva6_cantoMaximoMinimoMaximo() {
        Triangle triangle = new Triangle(MAX, 0, MAX);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA7: (Max, Max, 0) canto (Max, Max, 0) -> impossible")
    void bva7_cantoMaximoMaximoMinimo() {
        Triangle triangle = new Triangle(MAX, MAX, 0);

        assertEquals(IMPOSSIBLE, triangle.classify());
    }

    @Test
    @DisplayName("BVA8: (Max, Max, Max) canto (Max, Max, Max): único válido -> equilateral")
    void bva8_cantoMaximoMaximoMaximo() {
        Triangle triangle = new Triangle(MAX, MAX, MAX);

        assertEquals(EQUILATERAL, triangle.classify());
    }

    // ---- Equilátero (ECP2 e BVA 2.3) ----
    @Test
    @DisplayName("E1: (12, 12, 12) s1 = s2 = s3 -> equilateral")
    void e1_todosIguais() {
        Triangle triangle = new Triangle(12, 12, 12);

        assertEquals(EQUILATERAL, triangle.classify());
    }

    @Test
    @DisplayName("E2: (1, 1, 1) mínimo válido -> equilateral")
    void e2_minimoValido() {
        Triangle triangle = new Triangle(1, 1, 1);

        assertEquals(EQUILATERAL, triangle.classify());
    }

    // ---- Isósceles (ECP3 e BVA 2.3) ----
    @Test
    @DisplayName("Is1: (5, 5, 3) s1 = s2 ≠ s3 -> isosceles")
    void is1_s1IgualS2() {
        Triangle triangle = new Triangle(5, 5, 3);

        assertEquals(ISOSCELES, triangle.classify());
    }

    @Test
    @DisplayName("Is2: (3, 5, 5) s2 = s3 ≠ s1 -> isosceles")
    void is2_s2IgualS3() {
        Triangle triangle = new Triangle(3, 5, 5);

        assertEquals(ISOSCELES, triangle.classify());
    }

    @Test
    @DisplayName("Is3: (5, 3, 5) s1 = s3 ≠ s2 -> isosceles")
    void is3_s1IgualS3() {
        Triangle triangle = new Triangle(5, 3, 5);

        assertEquals(ISOSCELES, triangle.classify());
    }

    @Test
    @DisplayName("Is4: (2, 2, 1) lado diferente logo abaixo do igual -> isosceles")
    void is4_ladoDiferenteAbaixo() {
        Triangle triangle = new Triangle(2, 2, 1);

        assertEquals(ISOSCELES, triangle.classify());
    }

    @Test
    @DisplayName("Is5: (2, 2, 3) lado diferente logo acima do igual -> isosceles")
    void is5_ladoDiferenteAcima() {
        Triangle triangle = new Triangle(2, 2, 3);

        assertEquals(ISOSCELES, triangle.classify());
    }

    @Test
    @DisplayName("Is6: (Max-1, Max, Max) perto do limite máximo do int -> isosceles")
    void is6_pertoDoLimiteMaximo() {
        Triangle triangle = new Triangle(MAX - 1, MAX, MAX);

        assertEquals(ISOSCELES, triangle.classify());
    }

    // ---- Retângulo (ECP4 e BVA 2.3) ----
    @Test
    @DisplayName("R1: (5, 3, 4) s1² = s2² + s3² -> right-angled")
    void r1_hipotenusaEmS1() {
        Triangle triangle = new Triangle(5, 3, 4);

        assertEquals(RIGHT_ANGLED, triangle.classify());
    }

    @Test
    @DisplayName("R2: (3, 5, 4) s2² = s1² + s3² -> right-angled")
    void r2_hipotenusaEmS2() {
        Triangle triangle = new Triangle(3, 5, 4);

        assertEquals(RIGHT_ANGLED, triangle.classify());
    }

    @Test
    @DisplayName("R3: (3, 4, 5) s3² = s1² + s2² (fronteira de Pitágoras) -> right-angled")
    void r3_hipotenusaEmS3() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(RIGHT_ANGLED, triangle.classify());
    }

    // ---- Escaleno (ECP5 e BVA 2.3) ----
    @Test
    @DisplayName("Es1: (4, 5, 7) todos diferentes, sem ângulo reto -> scalene")
    void es1_todosDiferentes() {
        Triangle triangle = new Triangle(4, 5, 7);

        assertEquals(SCALENE, triangle.classify());
    }

    @Test
    @DisplayName("Es2: (2, 3, 4) s3 = s1 + s2 − 1 -> scalene")
    void es2_s3UmAbaixoDaFronteira() {
        Triangle triangle = new Triangle(2, 3, 4);

        assertEquals(SCALENE, triangle.classify());
    }

    @Test
    @DisplayName("Es3: (3, 4, 6) hipotenusa 5 passa a 6 -> scalene")
    void es3_logoAcimaDePitagoras() {
        Triangle triangle = new Triangle(3, 4, 6);

        assertEquals(SCALENE, triangle.classify());
    }

    // ============================================================
    // getArea()  (ECP6)
    // ============================================================

    @Test
    @DisplayName("A1: getArea(3, 4, 5) = 6.0")
    void a1_getArea_3_4_5() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(6.0, triangle.getArea(), DELTA);
    }

    @Test
    @DisplayName("A2: getArea(3, 3, 3) = 3.897 (área não inteira)")
    void a2_getArea_equilatero() {
        Triangle triangle = new Triangle(3, 3, 3);

        assertEquals(3.897, triangle.getArea(), DELTA);
    }

    @Test
    @DisplayName("A3: getArea(0, 1, 1) = -1 (lado a zero)")
    void a3_getArea_ladoZero() {
        Triangle triangle = new Triangle(0, 1, 1);

        assertEquals(-1.0, triangle.getArea(), DELTA);
    }

    @Test
    @DisplayName("A4: getArea(12, 2, 3) = -1 (desigualdade triangular)")
    void a4_getArea_desigualdadeTriangular() {
        Triangle triangle = new Triangle(12, 2, 3);

        assertEquals(-1.0, triangle.getArea(), DELTA);
    }

    // ============================================================
    // getPerimeter()  (ECP7)
    // ============================================================

    @Test
    @DisplayName("P1: getPerimeter(3, 4, 5) = 12")
    void p1_getPerimeter_3_4_5() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals(12, triangle.getPerimeter());
    }

    @Test
    @DisplayName("P2: getPerimeter(Max, Max, Max) = 6442450941 (soma real; overflow de int)")
    void p2_getPerimeter_overflow() {
        Triangle triangle = new Triangle(MAX, MAX, MAX);
        long somaReal = 3L * MAX; // 6442450941, não cabe num int

        assertEquals(somaReal, (long) triangle.getPerimeter());
    }

    // ============================================================
    // setSideLengths() e getSideLengths()  (ECP8 e ECP9)
    // ============================================================

    @Test
    @DisplayName("S1: setSideLengths(3, 4, 5) devolve this e guarda \"3,4,5\"")
    void s1_setSideLengths_valido() {
        Triangle triangle = new Triangle(1, 1, 1);

        Triangle devolvido = triangle.setSideLengths(3, 4, 5);

        // as duas verificações descrevem o mesmo resultado (uma chamada); assertAll reporta as duas
        assertAll(
                () -> assertSame(triangle, devolvido),
                () -> assertEquals("3,4,5", triangle.getSideLengths()));
    }

    @Test
    @DisplayName("S2: setSideLengths(-1, 2, 2) devolve this e guarda \"-1,2,2\"")
    void s2_setSideLengths_ladoNegativo() {
        Triangle triangle = new Triangle(1, 1, 1);

        Triangle devolvido = triangle.setSideLengths(-1, 2, 2);

        assertAll(
                () -> assertSame(triangle, devolvido),
                () -> assertEquals("-1,2,2", triangle.getSideLengths()));
    }

    @Test
    @DisplayName("S3: setSideLengths(Max, Max, Max) devolve this e guarda os valores")
    void s3_setSideLengths_max() {
        Triangle triangle = new Triangle(1, 1, 1);

        Triangle devolvido = triangle.setSideLengths(MAX, MAX, MAX);

        assertAll(
                () -> assertSame(triangle, devolvido),
                () -> assertEquals("2147483647,2147483647,2147483647", triangle.getSideLengths()));
    }

    @Test
    @DisplayName("G1: getSideLengths() = \"3,4,5\" (lados do construtor)")
    void g1_getSideLengths() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertEquals("3,4,5", triangle.getSideLengths());
    }

    // ============================================================
    // Métodos is*()  (ECP1 a ECP5)
    // ============================================================

    @Test
    @DisplayName("B1: isImpossible(2, 3, 5) = true (s3 = s1 + s2)")
    void b1_isImpossible_fronteira() {
        Triangle triangle = new Triangle(2, 3, 5);

        assertTrue(triangle.isImpossible());
    }

    @Test
    @DisplayName("B2: isEquilateral(3, 4, 3) = false (s1 = s3 mas s2 diferente)")
    void b2_isEquilateral_s1IgualS3() {
        Triangle triangle = new Triangle(3, 4, 3);

        assertFalse(triangle.isEquilateral());
    }

    @Test
    @DisplayName("B3: isIsosceles(3, 5, 5) = true")
    void b3_isIsosceles() {
        Triangle triangle = new Triangle(3, 5, 5);

        assertTrue(triangle.isIsosceles());
    }

    @Test
    @DisplayName("B4: isRightAngled(3, 4, 5) = true")
    void b4_isRightAngled() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertTrue(triangle.isRightAngled());
    }

    @Test
    @DisplayName("B5: isScalene(3, 4, 5) = false (tem ângulo reto)")
    void b5_isScalene_retangulo() {
        Triangle triangle = new Triangle(3, 4, 5);

        assertFalse(triangle.isScalene());
    }

    @Test
    @DisplayName("B6: isScalene(4, 5, 7) = true")
    void b6_isScalene() {
        Triangle triangle = new Triangle(4, 5, 7);

        assertTrue(triangle.isScalene());
    }
}
