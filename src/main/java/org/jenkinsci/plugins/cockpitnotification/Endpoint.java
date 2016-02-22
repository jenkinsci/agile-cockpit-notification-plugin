package org.jenkinsci.plugins.cockpitnotification;
/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import hudson.util.FormValidation;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;


public class Endpoint {

    public static final Integer DEFAULT_TIMEOUT = 30000;
    
    //public static final String DEFAULT_TEAM_TOKEN = "52026B18-ADF4-4073-AF49-ABF6A72D4272";
    
    private Protocol protocol;
    
    private Format format = Format.JSON;

    private String url;

    private String event = "all";

    private Integer timeout = DEFAULT_TIMEOUT;

    private Integer loglines = 0;

    private String teamtoken;
    
    private String encryptionkey;
    
    private String vectorkey;
    
    private String username;
    
    private String password;

    @DataBoundConstructor
    public Endpoint(Protocol protocol, String url,String event,
                    Format format, Integer timeout, Integer loglines, String teamtoken,String encryptionkey,String vectorkey)
    {
        setProtocol( protocol );
        setUrl( url );
        setEvent( event );
        setFormat( format );
        setTimeout( timeout );
        setLoglines( loglines );
        setTeamtoken(teamtoken);
        setEncryptionkey(encryptionkey);
        setVectorkey(vectorkey);
    }


    public void setTeamtoken(String teamtoken)
    {
        this.teamtoken = teamtoken;
    }

    public String getTeamtoken()
    {
        return this.teamtoken;
    }
    
    public void setEncryptionkey(String encryptionkey)
    {
        this.encryptionkey = encryptionkey;
    }

    public String getEncryptionkey()
    {
        return this.encryptionkey;
    }
    
     public void setVectorkey(String vectorkey)
    {
        this.vectorkey = vectorkey;
    }

    public String getVectorkey()
    {
        return this.vectorkey;
    }

    public int getTimeout() {
        return timeout == null ? DEFAULT_TIMEOUT : timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout =  timeout;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEvent (){
        return event;
    }

    public void setEvent ( String event ){
        this.event = event;
    }

    public Format getFormat() {
        if (this.format==null){
            this.format = Format.JSON;
        }
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Integer getLoglines() {
        return this.loglines;
    }

    public void setLoglines(Integer loglines) {
        this.loglines = loglines;
    }

    public FormValidation doCheckURL(@QueryParameter(value = "url", fixEmpty = true) String url) {
        if (url.equals("111"))
            return FormValidation.ok();
        else
            return FormValidation.error("There's a problem here");
    }

    public boolean isJson() {
        return getFormat() == Format.JSON;
    }

    @Override
    public String toString() {
        return protocol+":"+url;
    }
}
