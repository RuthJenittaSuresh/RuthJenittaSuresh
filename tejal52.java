import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Recipe {
    private String name;
    private ArrayList<String> ingredients;
    private String instructions;

    public Recipe(String name, ArrayList<String> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}

class RecipeManagementSystem extends JFrame implements ActionListener {
    private ArrayList<Recipe> recipes;
    private JTextField nameField;
    private JTextArea ingredientsArea;
    private JTextArea instructionsArea;

    public RecipeManagementSystem() {
        recipes = new ArrayList<>();

        setTitle("Recipe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Recipe Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(nameField, constraints);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ingredientsLabel, constraints);

        ingredientsArea = new JTextArea(5, 20);
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsArea);
        constraints.gridx = 1;
        panel.add(ingredientsScrollPane, constraints);

        JLabel instructionsLabel = new JLabel("Instructions:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(instructionsLabel, constraints);

        instructionsArea = new JTextArea(5, 20);
        JScrollPane instructionsScrollPane = new JScrollPane(instructionsArea);
        constraints.gridx = 1;
        panel.add(instructionsScrollPane, constraints);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Recipe");
        addButton.addActionListener(this);
        panel.add(addButton);

        JButton viewButton = new JButton("View Recipes");
        viewButton.addActionListener(this);
        panel.add(viewButton);

        return panel;
    }

    private void addRecipe() {
        String name = nameField.getText();
        String ingredientsText = ingredientsArea.getText();
        String instructions = instructionsArea.getText();

        if (name.isEmpty() || ingredientsText.isEmpty() || instructions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] ingredientsArray = ingredientsText.split("\n");
        ArrayList<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsArray) {
            if (!ingredient.trim().isEmpty()) {
                ingredients.add(ingredient);
            }
        }

        Recipe recipe = new Recipe(name, ingredients, instructions);
        recipes.add(recipe);

        JOptionPane.showMessageDialog(this, "Recipe added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        nameField.setText("");
        ingredientsArea.setText("");
        instructionsArea.setText("");
    }

    private void viewRecipes() {
        if (recipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No recipes available.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder output = new StringBuilder();
            for (Recipe recipe : recipes) {
                output.append("Name: ").append(recipe.getName()).append("\n");
                output.append("Ingredients:\n");
                for (String ingredient : recipe.getIngredients()) {
                    output.append("- ").append(ingredient).append("\n");
                }
                output.append("Instructions: ").append(recipe.getInstructions()).append("\n\n");
            }
            JOptionPane.showMessageDialog(this, output.toString(), "Recipes", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Add Recipe":
                addRecipe();
                break;
            case "View Recipes":
                viewRecipes();
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecipeManagementSystem();
        });
    }
}