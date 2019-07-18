import java.io.File
import java.text.SimpleDateFormat
import java.util.*

public class Main {


    companion object {

        var folderCount = 0
        val renamed_foldeers = arrayListOf<String>()
        var fileCount = 0
        var renamed_fileCount = 0

        @JvmStatic
        fun main(args: Array<String>) {

            println()
            args?.forEach {
                println(it)
            }
            val mainDir = File(args?.getOrNull(0) ?: "/home/shakir/Documents/Slices -updates")
            println()
            if (!mainDir.exists())
                println("mainDir NOT EXIST ===================")
            println("mainDir $mainDir")
            mainDir?.listFiles()?.forEach {
                renameFile(it)
            }

            println()
            println("Files:$fileCount Renamed:$renamed_fileCount")
            println("DIRS:$folderCount Renamed:${renamed_foldeers.size}")
            println("main completed")
        }


        fun renameFile(it: File) {
            if (it.isDirectory) {
                folderCount++
                it.listFiles().forEach {
                    renameFile(it)
                }
            } else {

                fileCount++
                if (
                    it.extension.equals("png", true) ||
                    it.extension.equals("jpg", true) ||
                    it.extension.equals("jpeg", true)
                ) {
                    var newName = it.name
                        .replace(" ", "_")
                        .replace("-", "_")
                        .replace("&", "_")
                        .replace("_____", "_")
                        .replace("____", "_")
                        .replace("___", "_")
                        .replace("__", "_")
                        .toLowerCase()

                    if (newName[0].toString().toIntOrNull() != null) {
                        newName = "image_$newName"
                    }


                    if (it.name != newName) {
                        if (File(it.parent, newName).exists()) {
                            val instance = Calendar.getInstance()
                            val formated = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(instance.time)
                            newName += formated
                        }
                        println()
                        println()
                        println("${it.name}:::$newName ${it.parent}")
                        renamed_fileCount++
                        it.renameTo(
                            File(
                                it.parent, newName
                            )
                        )

                        if (!renamed_foldeers.contains(it.parent))
                            renamed_foldeers.add(it.parent)

                    }

                }


            }
        }
    }
}