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

package eu.thesimplecloud.base.wrapper.process.filehandler

import eu.thesimplecloud.api.directorypaths.DirectoryPaths
import eu.thesimplecloud.api.service.version.ServiceVersion
import eu.thesimplecloud.api.utils.Downloader
import eu.thesimplecloud.api.utils.ZipUtils
import java.io.File
import java.io.FileNotFoundException

class ServiceVersionJarLoader {

    @Synchronized
    fun loadVersionFile(serviceVersion: ServiceVersion): File {
        val file = File(DirectoryPaths.paths.minecraftJarsPath + serviceVersion.name + ".jar")
        if (!file.exists()){
            if(serviceVersion.downloadURL.isBlank() || serviceVersion.downloadURL.equals("noDownload", ignoreCase = true))
                throw FileNotFoundException("Service version " + serviceVersion.name + " is set to no download but does not exist in minecraftJars folder.")

            Downloader().userAgentDownload(serviceVersion.downloadURL, file)
            //delete json to prevent bugs in spigot version 1.8
            ZipUtils().deletePath(file, "com/google/gson/")
            Thread.sleep(200)
        }
        return file
    }

}