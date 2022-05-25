import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.branchRemoteRun
import jetbrains.buildServer.configs.kotlin.buildSteps.python
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2022.04"

project {
    buildType(AnsibleVersion2)
}

object AnsibleVersion2 : BuildType({
    name = "ansiblev2"

    vcs {
        root(Ansible_1)
    }

    steps {
        script {
            scriptContent = "source ./hacking/env-setup"
        }
        python {
            environment = venv {
            }
            command = flake8 {
                scriptArguments = "--config=setup.cfg"
            }
        }
        python {
            environment = venv {
            }
            command = file {
                filename = "setup.py"
                scriptArguments = "install --user --install-scripts=."
            }
        }
    }

    triggers {
        branchRemoteRun {
            gitBranchPattern = "refs/heads/remote-run/devel"
        }
    }
})