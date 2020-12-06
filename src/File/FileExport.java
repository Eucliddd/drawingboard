package File;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @see FileExport
 * 将Group导出为图片
 * @version 1.0
 * @author 眭永熙
 */


public class FileExport {
    /**
     * 要保存的图形组
     */
    private Group savingGroup;

    public FileExport() {
    }

    public FileExport(Group gp) {
        savingGroup = gp;
    }

    /**
     * 设置组
     * @param gp 设置{@link #savingGroup}
     */
    public void setGroup(Group gp) {
        savingGroup = gp;
    }

    /**
     * 导出为png图片
     */
    public void export() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png"));
        File f = fc.showSaveDialog(null);
        try {
            WritableImage image = savingGroup.snapshot(new SnapshotParameters(), null);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
