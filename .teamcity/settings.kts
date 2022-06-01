import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

version = "2022.04"

project {
    buildType(HelloWorld)
    buildType(Ansible)
    vcsRoot(AnsibleRoot)
}

object HelloWorld: BuildType({
    name = "Hello world"
    steps {
        script {
            scriptContent = "echo 'Hello world!'"
        }
    }
})

object Ansible: BuildType({
    name = "Ansible"

    vcs {
        root(AnsibleRoot)
    }

    steps{
        python{
           environment = venv{
            }
            command = file {
                filename = "setup.py"
                scriptArguments = "install --user --install-scripts=."
            }
        }
    }
})

object AnsibleRoot: GitVcsRoot({
    name = "ansible_root_v2"
    url = "https://github.com/ansible/ansible.git"
    branch = "devel"
})