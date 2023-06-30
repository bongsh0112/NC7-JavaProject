package bitcamp.myapp.util;

import java.util.ArrayList;

public class Menu {

  private String title;
  private ArrayList<ActionListener> listeners = new ArrayList<>();

  public Menu(String title) {
    this.title = title;
  }

  public Menu(String title, ActionListener listener) {
    this(title);
    this.addActionListener(listener);
  }

  public void addActionListener(ActionListener listener) {
    listeners.add(listener);
  }

  public void removeActionListener(ActionListener listener) {
    listeners.remove(listener);
  }

  public String getTitle() {
    return title;
  }

  public void execute(BreadCrumbPrompt prompt) {
    for (ActionListener actionListener : listeners) {
      ActionListener listener = (ActionListener) actionListener;
      listener.service(prompt);
    }
  }
}
