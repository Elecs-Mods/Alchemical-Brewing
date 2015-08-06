package elec332.alchemicalbrewing.multiblock;

import elec332.alchemicalbrewing.AlchemicalBrewing;
import elec332.alchemicalbrewing.init.BlockRegister;
import elec332.core.multiblock.BlockData;
import elec332.core.multiblock.BlockStructure;
import elec332.core.multiblock.IMultiBlockStructure;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Elec332 on 29-7-2015.
 */
public final class MultiBlocks {

    public static void registerMultiBlocks(){
        AlchemicalBrewing.multiBlockRegistry.registerMultiBlock(new IMultiBlockStructure() {
            @Override
            public BlockStructure getStructure() {
                return new BlockStructure(3, 3, 3, new BlockStructure.IStructureFiller() {
                    @Override
                    public BlockData getBlockAtPos(int length, int width, int height) {
                        if (length == 1 && height == 1 && width == 1)
                            return null;
                        if (length == 1 && height == 0 && width == 0)
                            return new BlockData(BlockRegister.tankTap, OreDictionary.WILDCARD_VALUE);
                        return new BlockData(BlockRegister.mundaneTankBlock, OreDictionary.WILDCARD_VALUE);
                    }
                });
            }

            @Override
            public BlockStructure.IStructureFiller replaceUponCreated() {
                return null;
            }

            @Override
            public BlockData getTriggerBlock() {
                return new BlockData(BlockRegister.mundaneTankBlock, OreDictionary.WILDCARD_VALUE);
            }
        }, "MundaneTank", MundaneTank.class);
    }

}
