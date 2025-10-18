package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        // Lecture du fichier config.txt
        Scanner scanner = new Scanner(new File("D:\\my coding\\Feirouz\\TP1\\TP1\\src\\config.txt"));
        String daoClassName = scanner.nextLine();
        String metierClassName = scanner.nextLine();
        scanner.close();

        // Chargement dynamique des classes
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // Injection par réflexion
        Method setDao = cMetier.getMethod("setDao", IDao.class);
        setDao.invoke(metier, dao);

        // Affichage du résultat
        System.out.println("Résultats = " + metier.calcul());
    }
}
