class SendEmailWithAttachments {
    static void main(def args) {
        def date = LocalDataTime.now().format('yyyy-MM');
        def strEmailAddresses = "DL-NAM-SSIPatchReleaseNotes@ssi-schaefer.com";
        def msgBody  = "Please find a txt file per LLE server with the list of " + 
        "staged RPMs as determined using the below command:\n" +
        "\nyum check-update --exclude=kohls-Foundation*,zabbix*,*tibco*," + "
        *puppet*,*vmware*,*mq*,*tanium*,*percona*,*percona*,*mysql*,*mongo*," + "
        *cmon*,*kohls-mysql*,*mysql-cluster-commercial*,*mysql-commercial*," + "
        *mysql-router*,*mysql-shell*,*mysql-utilities*,*mysql-connector*," + "
        *mysql-cluster-commercial*,*openjdk*,*ca-certificates*\n"
        "\nThank you.";
        def ant = new AntBuilder();
        def workingDir = new File("/tmp");
        
        workingDir.eachFile() { file -> // What is this doing, looks like it is just casting the file name to string?
            String fileName = file.getName();
        }
        
        def filesList = workingDir.listFiles().grep(~/.*LLE_patching_rpms.txt$/);
        String[] fileString = '';

        for (currentFile in filesList) {
            fileCount++;
            def inputFile = new File(currentFile.toString());  // Looks like this is doing what Line 16 is for in a for-each loop which is what I need?
            fileString += f.canonicalPath; // Took parts from the for-loop from Stackoverflow and inserted into this for-each loop
            fileString += ",";
            /* 
            Stackoveflow code - Not sure we needed it all with the grep?
                String filesString = ""
                for (int i = 0; i < files.size(); i++) {
                    filesString += f.canonicalPath
                    if (files.size() > 1 && i < files.size() -1)
                        filesString += ","
            */

            inputFile.eachLine { line -> // Not sure this is needed, guessing it was just part of what you copied?
                if (line.contains("rows created") || line.contains("row created")) {
                    successCount++
                }    
            }
        }


            
        println(msgBody);
        ant.mail(mailhost:'lnmail01.kohls.com', mailport:'25', subject:"${date} - WAMAS LLE patching RPMs list by server", tolist:"${strEmailAddresses}");
        {
            from(address:'kt-sc-sre@kohls.com');
            replyto(address:'kt-sc-sre@kohls.com');
            message(msgBody);
            attachments() {
                fileset(fileString){
                    include(name:"" + dteNow.toString() + ".txt") // Is fileset(fileString) enough or does the include have do something from the Ant perspective?
                }
            }
        }
    }
}