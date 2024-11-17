
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyTreeApp {
    private static class TreeNode {
        String name;
        ArrayList<TreeNode> children;

        public TreeNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode child) {
            children.add(child);
        }

        public void printTree(String prefix) {
            System.out.println(prefix + name);
            for (TreeNode child : children) {
                child.printTree(prefix + "  ");
            }
        }
    }

    private static TreeNode root;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Family Tree Application!");

        System.out.print("Enter the root of the family tree (e.g., Grandparent): ");
        String rootName = scanner.nextLine();
        root = new TreeNode(rootName);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add family member");
            System.out.println("2. Display family tree");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFamilyMember(scanner);
                    break;
                case 2:
                    System.out.println("\nFamily Tree:");
                    root.printTree("");
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addFamilyMember(Scanner scanner) {
        System.out.print("Enter the name of the parent: ");
        String parentName = scanner.nextLine();

        TreeNode parent = findNode(root, parentName);
        if (parent == null) {
            System.out.println("Parent not found in the tree.");
            return;
        }

        System.out.print("Enter the name of the new family member: ");
        String childName = scanner.nextLine();
        TreeNode child = new TreeNode(childName);
        parent.addChild(child);
        System.out.println("Family member added successfully.");
    }

    private static TreeNode findNode(TreeNode current, String name) {
        if (current.name.equals(name)) {
            return current;
        }
        for (TreeNode child : current.children) {
            TreeNode result = findNode(child, name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}