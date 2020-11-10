package featurecat.lizzie.gui;

import featurecat.lizzie.Lizzie;
import featurecat.lizzie.util.DigitOnlyFilter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;

public class ToolBar extends JToolBar {
  public JTextField txtMoveNumber;
  private static final ResourceBundle resourceBundle = MainFrame.resourceBundle;

  public ToolBar() {
    super(
        BorderLayout.EAST.equals(Lizzie.config.toolbarPosition)
                || BorderLayout.WEST.equals(Lizzie.config.toolbarPosition)
            ? SwingConstants.VERTICAL
            : SwingConstants.HORIZONTAL);

    final JButton open = new JButton(resourceBundle.getString("ToolBar.open"));
    open.setFocusable(false);
    open.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.frame.openFile();
          }
        });
    add(open);
    addSeparator();

    final JButton save = new JButton(resourceBundle.getString("ToolBar.save"));
    save.setFocusable(false);
    save.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.frame.saveFile();
          }
        });
    add(save);
    addSeparator();

    final JButton analyze = new JButton(resourceBundle.getString("ToolBar.analyze"));
    analyze.setFocusable(false);
    analyze.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.leelaz.togglePonder();
            Lizzie.frame.refresh();
          }
        });
    add(analyze);
    addSeparator();

    final JButton kataEstimate = new JButton(resourceBundle.getString("ToolBar.kataEstimate"));
    kataEstimate.setFocusable(false);
    kataEstimate.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.config.showKataGoEstimate = !Lizzie.config.showKataGoEstimate;
            Lizzie.frame.removeEstimateRect();
            Lizzie.leelaz.ponder();
            Lizzie.config.uiConfig.put("show-katago-estimate", Lizzie.config.showKataGoEstimate);
            try {
              Lizzie.config.save();
            } catch (final IOException es) {
              // TODO Auto-generated catch block
            }
          }
        });
    add(kataEstimate);
    addSeparator();

    final JButton estimate = new JButton(resourceBundle.getString("ToolBar.estimate"));
    estimate.setFocusable(false);
    estimate.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.frame.toggleEstimateByZen();
          }
        });
    add(estimate);
    addSeparator();

    final JButton showPolicy = new JButton(resourceBundle.getString("ToolBar.showPolicy"));
    showPolicy.setFocusable(false);
    showPolicy.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.config.toggleShowPolicy();
          }
        });
    add(showPolicy);
    addSeparator();

    final JButton backMain = new JButton(resourceBundle.getString("ToolBar.backMain"));
    backMain.setFocusable(false);
    backMain.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            while (!Lizzie.board.getHistory().getCurrentHistoryNode().isMainTrunk()) {
              Lizzie.board.previousMove();
            }
          }
        });
    add(backMain);
    addSeparator();

    final JButton setMain = new JButton(resourceBundle.getString("ToolBar.setMain"));
    setMain.setFocusable(false);
    setMain.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            while (Lizzie.board.setAsMainBranch()) ;
          }
        });
    add(setMain);
    addSeparator();

    final JButton clearBoard = new JButton(resourceBundle.getString("ToolBar.clearBoard"));
    clearBoard.setFocusable(false);
    clearBoard.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.board.clear();
            Lizzie.frame.refresh(2);
          }
        });
    add(clearBoard);
    addSeparator();

    final JButton move = new JButton(resourceBundle.getString("ToolBar.move"));
    move.setFocusable(false);
    move.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.config.toggleShowMoveNumber();
            Lizzie.frame.refresh(2);
          }
        });
    add(move);
    addSeparator();

    final JButton coords = new JButton(resourceBundle.getString("ToolBar.coords"));
    coords.setFocusable(false);
    coords.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Lizzie.config.toggleCoordinates();
            Lizzie.frame.refresh(2);
          }
        });
    add(coords);
    addSeparator();

    final JButton gotoFirst = new JButton("|<");
    gotoFirst.setFocusable(false);
    gotoFirst.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            while (Lizzie.board.previousMove()) ;
          }
        });
    add(gotoFirst);

    final JButton back10 = new JButton("<<");
    back10.setFocusable(false);
    back10.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Input.undo(10);
          }
        });
    add(back10);

    final JButton back1 = new JButton("<");
    back1.setFocusable(false);
    back1.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Input.undo(1);
          }
        });
    add(back1);

    final JButton forward1 = new JButton(">");
    forward1.setFocusable(false);
    forward1.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Input.redo(1);
          }
        });
    add(forward1);

    final JButton forward10 = new JButton(">>");
    forward10.setFocusable(false);
    forward10.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            Input.redo(10);
          }
        });
    add(forward10);

    final JButton gotoEnd = new JButton(">|");
    gotoEnd.setFocusable(false);
    gotoEnd.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            while (Lizzie.board.nextMove()) ;
          }
        });
    add(gotoEnd);
    addSeparator();

    final NumberFormat nf = NumberFormat.getIntegerInstance();
    nf.setGroupingUsed(false);
    txtMoveNumber =
        new JFormattedTextField(
            new InternationalFormatter(nf) {
              protected DocumentFilter getDocumentFilter() {
                return filter;
              }

              private final DocumentFilter filter = new DigitOnlyFilter();
            });
    final JPanel panel = new JPanel(null);
    panel.setPreferredSize(new Dimension(100, 20));
    txtMoveNumber.setBounds(2, 1, 30, 18);
    panel.add(txtMoveNumber);

    final JButton gotoMove = new JButton(resourceBundle.getString("ToolBar.gotoMove"));
    gotoMove.setFocusable(false);
    gotoMove.setMargin(new Insets(0, 0, 0, 0));
    gotoMove.setBounds(32, 0, 40, 20);
    gotoMove.addActionListener(
        new ActionListener() {
          public void actionPerformed(final ActionEvent e) {
            try {
              Lizzie.board.goToMoveNumberBeyondBranch(Integer.parseInt(txtMoveNumber.getText()));
            } catch (final Exception ex) {
            }
            setTxtUnfocus();
          }
        });
    panel.add(gotoMove);

    add(panel);

    this.addComponentListener(
        new ComponentAdapter() {
          public void componentMoved(final ComponentEvent e) {
            if (Lizzie.frame != null) {
              Lizzie.frame.getToolBarPosition();
            }
          }

          public void componentResized(final ComponentEvent e) {
            if (Lizzie.frame != null) {
              Lizzie.frame.getToolBarPosition();
            }
          }
        });
  }

  public void setTxtUnfocus() {
    if (txtMoveNumber.isFocusOwner()) {
      txtMoveNumber.setFocusable(false);
      txtMoveNumber.setFocusable(true);
    }
  }
}
