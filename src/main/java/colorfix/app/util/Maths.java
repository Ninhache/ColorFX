package colorfix.app.util;

// Fonctions mathématiques supplémentaires non fournies dans la classe Math
public class Maths {
    public static double clamp(double x, double a, double b) {
        return Math.min(Math.max(x, a), b);
    }

    public static double clamp01(double x) {
        return clamp(x, 0, 1);
    }

    public static double map(double x, double startA, double endA, double startB, double endB) {
        return (x - startA) / (endA - startA) * (endB - startB) + startB;
    }
}
