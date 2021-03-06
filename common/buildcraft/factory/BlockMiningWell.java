/**
 * Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.factory;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import buildcraft.BuildCraftFactory;
import buildcraft.core.BlockLEDHatchBase;

public class BlockMiningWell extends BlockLEDHatchBase {
	public BlockMiningWell() {
		super(Material.ground);

		setHardness(5F);
		setResistance(10F);
		setStepSound(soundTypeStone);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		removePipes(world, x, y, z);
	}

	public void removePipes(World world, int x, int y, int z) {
		for (int depth = y - 1; depth > 0; depth--) {
			Block pipe = world.getBlock(x, depth, z);
			if (pipe != BuildCraftFactory.plainPipeBlock) {
				break;
			}
			world.setBlockToAir(x, depth, z);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMiningWell();
	}

	@Override
	public int getIconGlowLevel(IBlockAccess access, int x, int y, int z) {
		if (renderPass < 2) {
			return -1;
		} else {
			TileMiningWell tile = (TileMiningWell) access.getTileEntity(x, y, z);
			return tile.getIconGlowLevel(renderPass);
		}
	}
}
