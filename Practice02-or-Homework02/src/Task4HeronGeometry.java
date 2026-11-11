public class Task4HeronGeometry {
    public static void main(String[] args) {
        // Вхідні дані: сторони трикутника
        double a = 7.0;
        double b = 8.0;
        double c = 9.0;

        // 1. Периметр та напівпериметр
        double P = a + b + c;
        double p = P / 2.0;

        // 2. Площа за формулою Герона: S = sqrt(p * (p - a) * (p - b) * (p - c))
        double S = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        // 3. Вписане коло: радіус r = S / p, площа S_in = PI * r^2
        double r = S / p;
        double inCircleArea = Math.PI * Math.pow(r, 2);

        // 4. Описане коло: радіус R = (a * b * c) / (4 * S), площа S_out = PI * R^2
        double R = (a * b * c) / (4.0 * S);
        double outCircleArea = Math.PI * Math.pow(R, 2);

        // 5. Виведення геометрічного звіту
        System.out.println("================ ГЕОМЕТРИЧНИЙ ЗВІТ ================");
        System.out.println("Сторони: a = " + a + ", b = " + b + ", c = " + c);
        System.out.println("Периметр (P):               " + P);
        System.out.println("Напівпериметр (p):          " + p);
        System.out.println("Площа трикутника (S):       " + S + " кв. од.");
        System.out.println("Радіус вписаного кола (r):  " + r);
        System.out.println("Площа вписаного круга:      " + inCircleArea + " кв. од.");
        System.out.println("Радіус описаного кола (R):  " + R);
        System.out.println("Площа описаного круга:      " + outCircleArea + " кв. од.");
        System.out.println("==================================================");
    }
}
