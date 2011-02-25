package repository;

import gwt.jsChalleng.shared.WordGenerator;
import models.BlockEntry;
import models.BlockMem;

public class BlockEntryRepository {
	// return new BlockEntryDTO("   ", "?~~");
	// return new BlockEntryDTO("@   ", "_~~");
	// return new BlockEntryDTO("`   ", "~~~");

	public static BlockEntry getNewBlock() {
		BlockEntry be = BlockEntry.find("status = ?", 0).first();
		be.status = 1;
		be.save();
		
		return be;
	}
	
	public static void removeBlock(BlockEntry be) {
		be.delete();
	}
	
	public static void generateNewBlockEntries() {
		BlockMem mem = getBlockMem();
		WordGenerator wordGenerator = new WordGenerator(mem.lastBlock);

		for (int i = 0; i < 1000; i++) {
			// generate 3 blocks based on hardcoded values :\
			BlockEntry be = new BlockEntry(mem.lastBlock + "   ", mem.lastBlock
					+ "?~~");
			be.save();
			be = new BlockEntry(mem.lastBlock + "@  ", mem.lastBlock + "_~~");
			be.save();
			be = new BlockEntry(mem.lastBlock + "`  ", mem.lastBlock + "~~~");
			be.save();
			mem.lastBlock = wordGenerator.nextWord().toString();
		}
		System.out.println(mem.lastBlock);
		mem.save();

	}

	public static BlockMem getBlockMem() {
		if (BlockMem.count() == 0) {
			BlockMem mem = new BlockMem();
			mem.lastBlock = " ";
			mem.save();
			return mem;
		}

		// there's only one BlockMem
		return BlockMem.all().first();
	}

	// public static void

}
