import jetbrains.buildServer.configs.kotlin.v10.buildSteps.XcodeStep
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.*


version = "2022.04"

project {
    buildType(HelloWorld)
    buildType(Ansible)
}

object HelloWorld: BuildType({
    name = "Hello world"
    steps {
        script {
            command = "echo 'Hello world!'"
        }
        script {

        }
    }
})

object Ansible: BuildType({
    name = "ansible"

    vcs = 

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
})