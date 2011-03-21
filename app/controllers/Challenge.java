package controllers;

import models.BlockEntry;
import controllers.common.AuthController;

public class Challenge extends AuthController {

	public static void index() {
		System.out.println(BlockEntry.count());
		render();
	}
}
