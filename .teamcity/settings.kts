package TestSubProject.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python

object TestSubProject_BuildAnsible : BuildType({
    name = "build_ansible_v2"

    vcs {
        root(Ansible_1)
    }

    steps {
        python {
            workingDir = "ansible"
            environment = venv {
                pipArgs = "install"
            }
            command = file {
                filename = "setup.py"
            }
        }
    }
})