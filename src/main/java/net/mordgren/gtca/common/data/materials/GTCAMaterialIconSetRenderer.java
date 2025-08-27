package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.item.component.ICustomRenderer;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;

public class GTCAMaterialIconSetRenderer extends MaterialIconSet implements IRenderer {

    private final ICustomRenderer customRender;

    public GTCAMaterialIconSetRenderer(String name, MaterialIconSet parentIconset, boolean root, IRenderer renderer) {
        this(name, parentIconset, root, renderer == null ? null : () -> renderer);
    }
    public GTCAMaterialIconSetRenderer(String name, MaterialIconSet parentIconset, boolean root, ICustomRenderer renderer) {
        super(name, parentIconset, root);
        this.customRender = renderer;
    }
}
