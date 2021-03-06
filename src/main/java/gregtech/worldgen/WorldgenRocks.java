/**
 * Copyright (c) 2020 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOnSurface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenRocks extends WorldgenOnSurface {
	@SafeVarargs
	public WorldgenRocks(String aName, boolean aDefault, int aAmount, int aProbability, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aAmount, aProbability, aLists);
	}
	
	@Override
	public int canGenerate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return 0;
		for (String tName : aBiomeNames) if (BIOMES_DESERT.contains(tName) || BIOMES_MESA.contains(tName) || BIOMES_TAIGA.contains(tName) || BIOMES_SWAMP.contains(tName) || BIOMES_SAVANNA.contains(tName) || BIOMES_PLAINS.contains(tName) || BIOMES_WOODS.contains(tName) || BIOMES_MOUNTAINS.contains(tName) || BIOMES_WASTELANDS.contains(tName)) return mAmount;
		return 0;
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom, Block aContact) {
		if (aContact.getMaterial() != Material.grass && aContact.getMaterial() != Material.ground && aContact.getMaterial() != Material.sand) return F;
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return F;
		if (WD.easyRep(aWorld, aX, aY+1, aZ)) return tRegistry.mBlock.placeBlock(aWorld, aX, aY+1, aZ, SIDE_UNKNOWN, (short)32757, aRandom.nextInt(mAmount) == 0 ? ST.save(UT.NBT.make(), NBT_VALUE, aRandom.nextInt(12)==0?OP.rockGt.mat(MT.MeteoricIron, 1):ST.make(Items.flint, 1, 0)) : null, F, T);
		return F;
	}
}
