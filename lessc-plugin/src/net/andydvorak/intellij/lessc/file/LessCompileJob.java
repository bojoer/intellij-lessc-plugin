/*
 * Copyright 2012 Andrew C. Dvorak.
 *
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

package net.andydvorak.intellij.lessc.file;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;
import com.intellij.openapi.util.io.FileUtil;
import net.andydvorak.intellij.lessc.state.LessProfile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class LessCompileJob {

    private final LessFile lessFile;
    private final LessProfile lessProfile;
    private final Set<LessFile> modifiedLessFiles;
    private final Set<String> modifiedLessFilePaths;

    private File cssTempFile = null;
    private LessEngine lessEngine;

    public LessCompileJob(LessFile lessFile, LessProfile lessProfile) {
        this.lessFile = lessFile;
        this.lessProfile = lessProfile;
        this.modifiedLessFiles = new LinkedHashSet<LessFile>();
        this.modifiedLessFilePaths = new LinkedHashSet<String>();
    }

    public LessCompileJob(final LessCompileJob otherCompileJob, final LessFile lessFile) {
        this.lessFile = lessFile;
        this.lessProfile = otherCompileJob.getLessProfile();
        this.modifiedLessFiles = new LinkedHashSet<LessFile>(otherCompileJob.getModifiedLessFiles());
        this.modifiedLessFilePaths = new LinkedHashSet<String>(otherCompileJob.getModifiedLessFilePaths());
    }

    public LessFile getLessFile() {
        return lessFile;
    }

    public LessProfile getLessProfile() {
        return lessProfile;
    }

    public void addModifiedLessFile(LessFile lessFile) throws IOException {
        modifiedLessFiles.add(lessFile);
        modifiedLessFilePaths.add(lessFile.getCanonicalPath());
    }

    /**
     * @return a copy of the set of modified LESS files
     */
    public Set<LessFile> getModifiedLessFiles() {
        return new LinkedHashSet<LessFile>(modifiedLessFiles);
    }

    /**
     * @return a copy of the set of modified LESS files
     */
    public Set<String> getModifiedLessFilePaths() {
        return new LinkedHashSet<String>(modifiedLessFilePaths);
    }

    public int getNumModified() {
        return getModifiedLessFiles().size();
    }

    public File getCssTempFile() throws IOException {
        if ( cssTempFile == null ) {
            cssTempFile = FileUtil.createTempFile("intellij-lessc-plugin.", ".css", true);
        }
        return cssTempFile;
    }

    public void compile() throws IOException, LessException {
        if (lessEngine == null) {
            lessEngine = new LessEngine();
        }
        lessFile.compile(lessEngine, getCssTempFile(), lessProfile.isCompressOutput());
    }
}
