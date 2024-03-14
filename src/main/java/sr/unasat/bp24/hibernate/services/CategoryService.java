package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.CategoryRepo;
import sr.unasat.bp24.hibernate.entity.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryService {

    public static List<Category> getAllCategories() {
        CategoryRepo categoryRepo = new CategoryRepo();
        return categoryRepo.getAllCategories();
    }

    public static Category selectCategory() {
        Scanner sc = new Scanner(System.in);

        int selectedCategory = 0;

        boolean notSelected = true;

        List<Category> categoryList = getAllCategories();

        while (notSelected) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                System.out.println((i + 1) + ".: " + categoryList.get(i).getCategoryName());
            }
            selectedCategory = sc.nextInt() - 1;
            notSelected = false;
        }

        Category category = categoryList.get(selectedCategory);

        return category;
    }
}
