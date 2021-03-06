package org.jenkinsci.plugins.cockpitnotification;

/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;

public enum Format {
    XML {
        private final XStream xstream = new XStream();

        @Override
        protected byte[] serialize(JobState jobState, Endpoint target) throws IOException, Throwable {
            xstream.processAnnotations(JobState.class);
            return xstream.toXML(jobState).getBytes("UTF-8");
        }
    },
    JSON {
        private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        @Override
        protected byte[] serialize(JobState jobState, Endpoint target) throws IOException, Throwable {
            String encryptionKey = target.getEncryptionkey();
            String initialVector = target.getVectorkey();

            String encryptionData = "";
            try {
                TripleDES tripleDes = new TripleDES(encryptionKey, initialVector);
                encryptionData = tripleDes.encryptText(gson.toJson(jobState));
            } catch (Exception exception) {
                throw exception;
            }

            return encryptionData.getBytes("UTF-8");
        }
    };

    protected abstract byte[] serialize(JobState jobState, Endpoint target) throws IOException, Throwable;
}
