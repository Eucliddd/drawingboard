package File;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @see FileSave
 * 可编辑文件保存类
 * @version 1.0
 * @author 眭永熙
 */
public class FileSave {
    private Group savingGroup;

    public FileSave() {
    }

    public FileSave(Group gp) {
        savingGroup = gp;
    }

    public void setGroup(Group gp) {
        savingGroup = gp;
    }

    /**
     * 保存可编辑文件edit
     */
    public void save() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Edit", "*.edit"));
        File f = fc.showSaveDialog(null);
        try {
            FileWriter fw = new FileWriter(f.getPath());
            for (Node nd : savingGroup.getChildren()) {
                fw.write(nd.toString());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
