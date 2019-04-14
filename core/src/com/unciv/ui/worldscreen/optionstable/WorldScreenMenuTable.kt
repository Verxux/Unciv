package com.unciv.ui.worldscreen.optionstable

import com.unciv.UnCivGame
import com.unciv.models.gamebasics.tr
import com.unciv.ui.CivilopediaScreen
import com.unciv.ui.NewGameScreen
import com.unciv.ui.VictoryScreen
import com.unciv.ui.mapeditor.MapEditorScreen
import com.unciv.ui.pickerscreens.PolicyPickerScreen
import com.unciv.ui.saves.LoadScreen
import com.unciv.ui.saves.SaveScreen
import com.unciv.ui.worldscreen.WorldScreen

class WorldScreenMenuTable(val worldScreen: WorldScreen) : PopupTable(worldScreen) {

    init {
        addButton("Map editor".tr()){
            val tileMapClone = worldScreen.gameInfo.tileMap.clone()
            for(tile in tileMapClone.values){
                tile.militaryUnit=null
                tile.civilianUnit=null
            }
            UnCivGame.Current.screen = MapEditorScreen(tileMapClone)
            remove()
        }

        addButton("Civilopedia".tr()){
            UnCivGame.Current.screen = CivilopediaScreen()
            remove()
        }

        addButton("Load game".tr()){
            UnCivGame.Current.screen = LoadScreen()
            remove()
        }

        addButton("Save game".tr()) {
            UnCivGame.Current.screen = SaveScreen()
            remove()
        }

        addButton("Start new game".tr()){ UnCivGame.Current.screen = NewGameScreen() }

        addButton("Victory status".tr()) { UnCivGame.Current.screen = VictoryScreen() }

        addButton("Social policies".tr()){
            UnCivGame.Current.screen = PolicyPickerScreen(UnCivGame.Current.gameInfo.getCurrentPlayerCivilization())
        }


        addButton("Options".tr()){
            UnCivGame.Current.worldScreen.stage.addActor(WorldScreenOptionsTable(worldScreen))
            remove()
        }

        addButton("Close".tr()){ remove() }

        open()
    }
}

