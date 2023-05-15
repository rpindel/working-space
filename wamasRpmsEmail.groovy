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
        
        def filesList = workingDir.listFiles().grep(~/.*LLE_patching_rpms.txt$/);
        String fileString = "";

        for (currentFile in filesList) {
            def fileCount = 0;
            fileCount++;
            // currentFile.toString();
            fileString += currentFile.canonicalPath;
            if (fileCount <= 3) {
                fileString += ",";    
            }
        }    
            
            /* 
            Stackoveflow code - Not sure we needed it all with the grep?
                String filesString = ""
                for (int i = 0; i < files.size(); i++) {
                    filesString += f.canonicalPath
                    if (files.size() > 1 && i < files.size() -1)
                        filesString += ","
            */

            
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