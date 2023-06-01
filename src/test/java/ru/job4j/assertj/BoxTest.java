package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 2);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotEqualTo("Cube")
                .isNotNull()
                .doesNotContain("Java")
                .isEqualTo("Sphere");
        assertThat(box.getArea()).isNotZero()
                .isPositive()
                .isGreaterThan(48)
                .isLessThan(60)
                .isEqualTo(50.26d, withPrecision(0.01d));
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 2);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotEqualTo("Cube")
                .startsWith("Tet")
                .endsWith("on")
                .isEqualTo("Tetrahedron");
        assertThat(box.getArea()).isNotZero()
                .isNotEqualTo(6.8)
                .isPositive()
                .isEqualTo(6.92d, withPrecision(0.01d));
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Sph")
                .isEqualTo("Cube");
        assertThat(box.getArea()).isPositive()
                .isNotEqualTo(216.1)
                .isEqualTo(216.00d, withPrecision(0.01d));
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(10, 0);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotNull()
                .isEqualTo("Unknown object");
        assertThat(box.getArea()).isNotNull()
                .isNotNegative()
                .isNotPositive()
                .isEqualTo(0.00d);
    }

    @Test
    void whenBoxIsNotExistThenFalse() {
        Box box = new Box(-10, 5);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenBoxIsExistThenTrue() {
        Box box = new Box(4, 5);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenVertexIsGreaterThen0ThenNumberOfVerticesIsLessThen0() {
        Box box = new Box(3, 5);
        assertThat(box.getNumberOfVertices())
                .isNotPositive()
                .isLessThan(0);
    }

    @Test
    void whenVertexIsLessThen0ThenNumberOfVerticesIsLessThen0() {
        Box box = new Box(-4, 5);
        assertThat(box.getNumberOfVertices())
                .isNotPositive()
                .isLessThan(0);
    }
}