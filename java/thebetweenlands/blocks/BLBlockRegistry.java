package thebetweenlands.blocks;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import thebetweenlands.blocks.terrain.BlockAlgae;
import thebetweenlands.blocks.terrain.BlockBetweenlandsBedrock;
import thebetweenlands.blocks.terrain.BlockBetweenstone;
import thebetweenlands.blocks.terrain.BlockGenericOre;
import thebetweenlands.blocks.terrain.BlockGenericStone;
import thebetweenlands.blocks.terrain.BlockMud;
import thebetweenlands.blocks.terrain.BlockSilt;
import thebetweenlands.blocks.terrain.BlockSwampDirt;
import thebetweenlands.blocks.terrain.BlockSwampGrass;
import thebetweenlands.blocks.terrain.BlockSwampWater;
import thebetweenlands.items.ItemMaterialsBL.EnumMaterialsBL;
import cpw.mods.fml.common.registry.GameRegistry;

public class BLBlockRegistry
{
	// LIST WITH ALL BLOCKS IN THIS CLASS
	public static final List<Block> BLOCKS = new LinkedList<Block>();

	// PORTAL
	public static BlockPortal portalBlock = new BlockPortal();

	// TERRAIN
	public static final Block betweenstone = new BlockBetweenstone();
	public static final Block genericStone = new BlockGenericStone();
	public static final Block druidStone1 = new BlockDruidStone("druidStone1");
	public static final Block druidStone2 = new BlockDruidStone("druidStone2");
	public static final Block druidStone3 = new BlockDruidStone("druidStone3");
	public static final Block druidStone4 = new BlockDruidStone("druidStone4");
	public static final Block druidStone5 = new BlockDruidStone("druidStone5");
	public static final Block betweenlandsBedrock = new BlockBetweenlandsBedrock();
	public static final Block swampDirt = new BlockSwampDirt();
	public static final Block swampGrass = new BlockSwampGrass();
	public static final Block silt = new BlockSilt();
	public static final Block mud = new BlockMud();
	public static final Block swampWater = new BlockSwampWater();
	public static final Block algae = new BlockAlgae();
	public static final Block sulfurTorch = new BlockSulfurTorch();
	
	// ORES
	public static final Block aquaMiddleGemOre = new BlockGenericOre("aquaMiddleGemOre", EnumMaterialsBL.AQUA_MIDDLE_GEM);
	public static final Block crimsonMiddleGemOre = new BlockGenericOre("crimsonMiddleGemOre", EnumMaterialsBL.CRIMSON_MIDDLE_GEM);
	public static final Block greenMiddleGemOre = new BlockGenericOre("greenMiddleGemOre", EnumMaterialsBL.GREEN_MIDDLE_GEM);
	public static final Block octineOre = new BlockGenericOre("octineOre", null);
	public static final Block sulfurOre = new BlockGenericOre("sulfurOre", EnumMaterialsBL.SULFUR);
	public static final Block valoniteOre = new BlockGenericOre("valoniteOre", EnumMaterialsBL.VALONITE_SHARD);
	public static final Block lifeCrystalOre = new BlockGenericOre("lifeCrystalOre", EnumMaterialsBL.LIFE_CRYSTAL);
	
	// WOOD

	// DOUBLE PLANTS

	// SMALL PLANTS

	// UNDERGROWTH

	// DECORATIONS AND UTILITIES

	// ALTARS
	public static final Block druidAltar = new BlockDruidAltar();

	// STAIRS, SLABS, WALLS

	// OTHER THINGS
	public static final Block druidSpawner = new BlockDruidSpawner("darkDruid");

	public static void init() {
		initBlocks();
		registerBlocks();
		registerProperties();
	}

	private static void initBlocks() {

	}

	private static void registerBlocks() {
		try {
			for (Field f : BLBlockRegistry.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block)
					registerBlock((Block) obj);
				else if (obj instanceof Block[])
					for (Block block : (Block[]) obj)
						registerBlock(block);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerBlock(Block block) {
		BLOCKS.add(block);
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");

		if (block instanceof ISubBlocksBlock)
			GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
		else
			GameRegistry.registerBlock(block, strings[strings.length - 1]);
	}

	private static void registerProperties() {
		//for fire etc
	}

	public static interface ISubBlocksBlock
    {
		Class<? extends ItemBlock> getItemBlockClass();
	}
}
