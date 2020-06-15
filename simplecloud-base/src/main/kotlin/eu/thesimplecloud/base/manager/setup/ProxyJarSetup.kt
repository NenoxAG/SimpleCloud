/*
 * MIT License
 *
 * Copyright (C) 2020 The SimpleCloud authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package eu.thesimplecloud.base.manager.setup

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.version.type.ServiceVersionType
import eu.thesimplecloud.api.utils.Downloader
import eu.thesimplecloud.launcher.console.setup.ISetup
import eu.thesimplecloud.launcher.console.setup.annotations.SetupQuestion
import eu.thesimplecloud.launcher.extension.sendMessage
import eu.thesimplecloud.launcher.startup.Launcher
import java.io.File

class ProxyJarSetup(private val proxyFile: File) : ISetup {

    @SetupQuestion(0, "manager.setup.proxy-jar.question", "Which proxy version do you want to use? (Bungeecord, Waterfall, Travertine, Hexacord, Velocity)")
    fun setup(answer: String): Boolean {
        val serviceVersion = CloudAPI.instance.getServiceVersionHandler().getVersionsByPrefix(answer).firstOrNull()
        if (serviceVersion == null || serviceVersion.serviceAPIType.serviceVersionType != ServiceVersionType.PROXY) {
            Launcher.instance.consoleSender.sendMessage("manager.setup.proxy-jar.version-invalid", "The specified version is invalid.")
            return false
        }
        Launcher.instance.consoleSender.sendMessage("manager.setup.proxy-jar.downloading", "Downloading proxy...")
        Downloader().userAgentDownload(serviceVersion.downloadURL, proxyFile)
        return true
    }

}
