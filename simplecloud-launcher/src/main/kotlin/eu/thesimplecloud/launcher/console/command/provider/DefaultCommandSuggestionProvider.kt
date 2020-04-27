package eu.thesimplecloud.launcher.console.command.provider

import eu.thesimplecloud.api.command.ICommandSender
import eu.thesimplecloud.api.player.ICloudPlayer

/**
 * Created by IntelliJ IDEA.
 * User: Philipp.Eistrach
 * Date: 14.04.2020
 * Time: 18:39
 */
class DefaultCommandSuggestionProvider: ICommandSuggestionProvider {

    override fun getSuggestions(sender: ICommandSender, lastArgument: String): List<String> {
        return emptyList()
    }

}