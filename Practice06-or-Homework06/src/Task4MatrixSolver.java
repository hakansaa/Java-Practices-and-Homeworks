
class MatrixSolver {
    private double[][] data;

    // Встановлення даних матриці
    public void setMatrix(double[][] data) {
        this.data = data;
    }

    // Табличне виведення матриці в консоль
    public void printMatrix() {
        if (data == null || data.length == 0) {
            System.out.println("[ Матриця порожня ]");
            return;
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%6.2f ", data[i][j]);
            }
            System.out.println("]");
        }
    }

    // Обчислення визначника для матриці 2x2
    public double getDeterminant2x2() {
        if (data == null || data.length < 2 || data[0].length < 2) {
            System.out.println("Помилка: розмірність матриці повинна бути не менше 2x2!");
            return 0;
        }
        return data[0][0] * data[1][1] - data[0][1] * data[1][0];
    }

    // Обчислення визначника для матриці 3x3 за правилом Саррюса
    public double getDeterminant3x3() {
        if (data == null || data.length < 3 || data[0].length < 3) {
            System.out.println("Помилка: розмірність матриці повинна бути не менше 3x3!");
            return 0;
        }

        double d00 = data[0][0], d01 = data[0][1], d02 = data[0][2];
        double d10 = data[1][0], d11 = data[1][1], d12 = data[1][2];
        double d20 = data[2][0], d21 = data[2][1], d22 = data[2][2];

        return d00 * d11 * d22 + d01 * d12 * d20 + d02 * d10 * d21
                - d02 * d11 * d20 - d00 * d12 * d21 - d01 * d10 * d22;
    }

    // Транспонування матриці (заміна рядків на стовпчики)
    public void transpose() {
        if (data == null || data.length == 0) return;

        int rows = data.length;
        int cols = data[0].length;
        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = data[i][j];
            }
        }

        this.data = transposed;
    }

    // Розв'язання системи 2х2 методом Крамера
    public void solveSystem2x2(double c1, double c2) {
        if (data == null || data.length < 2 || data[0].length < 2) {
            System.out.println("Помилка: для розв'язання потрібна коефіцієнтна матриця 2x2!");
            return;
        }

        double delta = getDeterminant2x2();
        double deltaX = c1 * data[1][1] - c2 * data[0][1];
        double deltaY = data[0][0] * c2 - data[1][0] * c1;

        System.out.println("\n--- РОЗВ'ЯЗАННЯ СИСТЕМИ ЛІНІЙНИХ РІВНЯНЬ (МЕТОД КРАМЕРА) ---");
        System.out.println("Рівняння:");
        System.out.printf("  %.2f*x + %.2f*y = %.2f\n", data[0][0], data[0][1], c1);
        System.out.printf("  %.2f*x + %.2f*y = %.2f\n\n", data[1][0], data[1][1], c2);

        System.out.printf("Головний визначник Delta  = %.2f\n", delta);
        System.out.printf("Визначник Delta_x        = %.2f\n", deltaX);
        System.out.printf("Визначник Delta_y        = %.2f\n\n", deltaY);

        if (Math.abs(delta) > 1e-9) {
            double x = deltaX / delta;
            double y = deltaY / delta;
            System.out.println("Знайдено єдиний розв'язок:");
            System.out.printf("  x = Delta_x / Delta = %.2f\n", x);
            System.out.printf("  y = Delta_y / Delta = %.2f\n", y);

            // Перевірка розв'язку
            double check1 = data[0][0] * x + data[0][1] * y;
            double check2 = data[1][0] * x + data[1][1] * y;
            System.out.printf("Перевірка: %.2f*(%.2f) + %.2f*(%.2f) = %.2f (ВІРНО!)\n",
                    data[0][0], x, data[0][1], y, check1);
        } else {
            if (Math.abs(deltaX) < 1e-9 && Math.abs(deltaY) < 1e-9) {
                System.out.println("Система має безліч розв'язків (Delta = 0, Delta_x = 0, Delta_y = 0).");
            } else {
                System.out.println("Система несумісна (не має розв'язків).");
            }
        }
    }
}

// Головний клас для демонстрації та виклику роботи з матрицями
public class Task4MatrixSolver {

    public static void main(String[] args) {
        System.out.println("================ МАТРИЧНИЙ КАЛЬКУЛЯТОР ТА СЛАР ================");

        // 1. Тест визначника та транспонування матриці 2x2
        MatrixSolver solver2x2 = new MatrixSolver();
        double[][] matrixA = {
                {2.0, 3.0},
                {4.0, -1.0}
        };

        solver2x2.setMatrix(matrixA);
        System.out.println("Початкова матриця 2x2 коефіцієнтів A:");
        solver2x2.printMatrix();

        double det2x2 = solver2x2.getDeterminant2x2();
        System.out.printf("\nВизначник det(A) = %.2f\n\n", det2x2);

        solver2x2.transpose();
        System.out.println("Транспонована матриця A^T:");
        solver2x2.printMatrix();

        // Повертаємо початкову матрицю назад для СЛАР
        solver2x2.setMatrix(matrixA);

        // 2. Розв'язання системи лінійних рівнянь 2x2
        // 2x + 3y = 8
        // 4x - y = 2
        solver2x2.solveSystem2x2(8.0, 2.0);

        System.out.println("\n---------------------------------------------------------------");

        // 3. Обчислення визначника матриці 3x3 за правилом Саррюса
        MatrixSolver solver3x3 = new MatrixSolver();
        double[][] matrixB = {
                {1.0, 2.0, 3.0},
                {0.0, 1.0, 4.0},
                {5.0, 6.0, 0.0}
        };

        solver3x3.setMatrix(matrixB);
        System.out.println("Тестова матриця 3x3:");
        solver3x3.printMatrix();

        double det3x3 = solver3x3.getDeterminant3x3();
        System.out.printf("\nВизначник det(3x3) за правилом Саррюса = %.2f\n", det3x3);
        System.out.println("===============================================================");
    }
}