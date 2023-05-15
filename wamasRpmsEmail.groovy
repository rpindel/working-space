class SendEmailWithAttachments {
    static void main(def args) {
        def date = new java.text.SimpleDateFormat("yyyy-MM").format(new Date());
        def workingDir = new File("/tmp");
        def ant = new AntBuilder();
        def strEmailAddresses = "DL-NAM-SSIPatchReleaseNotes@ssi-schaefer.com";
        def msgBody  = "Please find a txt file per LLE server with the list of " + 
        "staged RPMs as determined using the below command:\n" +
        "\nyum check-update --exclude=kohls-Foundation*,zabbix*,*tibco*," + "
        *puppet*,*vmware*,*mq*,*tanium*,*percona*,*percona*,*mysql*,*mongo*," + "
        *cmon*,*kohls-mysql*,*mysql-cluster-commercial*,*mysql-commercial*," + "
        *mysql-router*,*mysql-shell*,*mysql-utilities*,*mysql-connector*," + "
        *mysql-cluster-commercial*,*openjdk*,*ca-certificates*\n"
        "\nThank you.";   
            
        println(msgBody);
        ant.mail(mailhost:'lnmail01.kohls.com', mailport:'25', subject:"${date} - WAMAS LLE patching RPMs list by server", tolist:"${strEmailAddresses}") {
            from(address:'kt-sc-sre@kohls.com');
            replyto(address:'kt-sc-sre@kohls.com');
            message(msgBody);
            attachments() {
                fileset(dir: workingDir) {
                    include(name: "*.txt");
                }
            }
        }
    }
}